(defn pancake_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(flip-prefix [v k]
            (into (vec (reverse (subvec v 0 k))) (subvec v k)))
          (max-index-up-to [v end]
            (reduce (fn [best i]
                      (if (> (compare (v i) (v best)) 0) i best))
                    0
                    (range 1 end)))
          (sort-step [v end]
            (if (<= end 1)
              v
              (let [mi (max-index-up-to v end)]
                (cond
                  (= mi (dec end)) (sort-step v (dec end))
                  (zero? mi) (sort-step (flip-prefix v end) (dec end))
                  :else (sort-step (flip-prefix (flip-prefix v (inc mi)) end)
                                   (dec end))))))]
    (if (seq nums)
      (seq (sort-step (vec nums) (count nums)))
      nums)))