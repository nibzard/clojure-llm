(defn pancake_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(flip-prefix [v k]
            (into (subvec v k) (rseq (subvec v 0 k))))
          (max-index [v n]
            (reduce (fn [mi i]
                      (if (> (v i) (v mi)) i mi))
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
    (if (seq nums)
      (let [v (vec nums)]
        (into (empty nums) (sort-step v (count v))))
      nums)))