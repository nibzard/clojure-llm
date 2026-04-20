(defn pancake_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(flip-prefix [v k]
            (into (subvec v k) (rseq (subvec v 0 k))))
          (max-index [v end]
            (reduce (fn [max-i i]
                      (if (> (nth v i) (nth v max-i)) i max-i))
                    0
                    (range 1 end)))
          (sort-step [v end]
            (if (<= end 1)
              v
              (let [mi (max-index v end)]
                (cond
                  (= mi (dec end)) (recur v (dec end))
                  (zero? mi) (recur (flip-prefix v end) (dec end))
                  :else (recur (flip-prefix (flip-prefix v (inc mi)) end) (dec end))))))]
    (if (seq nums)
      (let [v (vec nums)
            sorted-v (sort-step v (count v))]
        (if (vector? nums) sorted-v (apply list sorted-v)))
      nums)))