(defn pancake_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(flip-prefix [v k]
            (into (subvec v k)
                  (rseq (subvec v 0 k))))
          (sort-step [v n]
            (if (<= n 1)
              v
              (let [max-idx (first
                              (reduce-kv
                                (fn [[mi mv] i x]
                                  (if (> x mv) [i x] [mi mv]))
                                [0 (v 0)]
                                (subvec v 0 n)))]
                (cond
                  (= max-idx (dec n))
                  (recur v (dec n))

                  (zero? max-idx)
                  (recur (flip-prefix v n) (dec n))

                  :else
                  (recur (-> v
                             (flip-prefix (inc max-idx))
                             (flip-prefix n))
                         (dec n))))))]
    (let [v (vec (or nums []))]
      (seq (sort-step v (count v))))))