(defn max_sum
  "Write a function that takes a vector and finds the maximum sum of a bitonic subsequence for the given vector, where a sequence is bitonic if it is first increasing and then decreasing."
  [arr]
  (let [n (count arr)]
    (if (zero? n)
      0
      (let [nums (vec arr)
            ;; Calculate Maximum Sum Increasing Subsequence (MSIS) ending at i
            msis (long-array (map identity nums))
            _ (doseq [i (range 1 n)]
                (doseq [j (range i)]
                  (when (and (< (nums j) (nums i))
                             (< (aget msis j) (aget msis i)))
                    (aset msis i (+ (aget msis i) (aget msis j))))))
            
            ;; Calculate Maximum Sum Decreasing Subsequence (MSDS) starting at i
            msds (long-array (map identity nums))
            _ (doseq [i (range (- n 2) -1 -1)]
                (doseq [j (range (- n 1) i -1)]
                  (when (and (< (nums j) (nums i))
                             (< (aget msds j) (aget msds i)))
                    (aset msds i (+ (aget msds i) (aget msds j))))))
            
            ;; Find maximum sum of bitonic subsequence
            max-sum (atom (aget msis 0))]
        (doseq [i (range n)]
          ;; Subtract nums[i] once because it was added in both MSIS and MSDS
          (let [current-sum (- (+ (aget msis i) (aget msds i)) (nums i))]
            (when (> current-sum @max-sum)
              (reset! max-sum current-sum))))
        @max-sum))))