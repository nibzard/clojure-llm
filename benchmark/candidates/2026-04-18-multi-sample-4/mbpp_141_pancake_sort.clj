(defn pancake_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(flip-prefix [v k]
            (into (vec (reverse (subvec v 0 k))) (subvec v k)))
          (max-index [v n]
            (reduce (fn [best i]
                      (if (> (compare (v i) (v best)) 0) i best))
                    0
                    (range 1 n)))
          (sort-step [v n]
            (if (<= n 1)
              v
              (let [mi (max-index v n)]
                (cond
                  (= mi (dec n)) (sort-step v (dec n))
                  (zero? mi)     (sort-step (flip-prefix v n) (dec n))
                  :else          (sort-step (flip-prefix (flip-prefix v (inc mi)) n)
                                            (dec n))))))]
    (cond
      (nil? nums) nil
      (empty? nums) nums
      :else (let [v (vec nums)
                  sorted-v (sort-step v (count v))]
              (if (vector? nums) sorted-v (seq sorted-v))))))