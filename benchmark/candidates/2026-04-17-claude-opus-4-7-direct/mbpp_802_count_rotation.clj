(defn count_rotation
  "Write a cljthon function to count the number of rotations required to generate a sorted vector. https://www.geeksforgeeks.org/count-of-rotations-required-to-generate-a-sorted-vector/"
  [arr]
  (if (or (nil? arr) (empty? arr) (<= (count arr) 1))
    0
    (let [cnt (count arr)
          ;; Helper function to find the index of the minimum element
          ;; using binary search
          find-min-idx (fn [low high]
                         (loop [low low
                                high high]
                           (let [mid (quot (+ low high) 2)
                                 prev (mod (dec mid) cnt)
                                 next (mod (inc mid) cnt)
                                 mid-val (nth arr mid)
                                 prev-val (nth arr prev)
                                 next-val (nth arr next)]
                             (cond
                               ;; If mid element is smaller than both neighbors, it is the minimum
                               (and (< mid-val next-val)
                                    (< mid-val prev-val))
                               mid
                               
                               ;; If the right half (mid to high) is sorted, the minimum is in the left half
                               (<= (nth arr high) mid-val)
                               (recur low (dec mid))
                               
                               ;; Otherwise, the minimum is in the right half
                               :else
                               (recur (inc mid) high)))))]
      (find-min-idx 0 (dec cnt)))))