(defn pancake_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(flip-prefix [v k]
            (into (vec (reverse (subvec v 0 k))) (subvec v k)))
          (max-index [v n]
            (reduce (fn [mi i]
                      (if (> (nth v i) (nth v mi)) i mi))
                    0
                    (range 1 n)))
          (sort-step [v n]
            (if (<= n 1)
              v
              (let [mi (max-index v n)]
                (cond
                  (= mi (dec n)) (recur v (dec n))
                  (zero? mi) (recur (flip-prefix v n) (dec n))
                  :else (recur (flip-prefix (flip-prefix v (inc mi)) n) (dec n))))))]
    (if (nil? nums)
      nil
      (let [v (vec nums)]
        (seq (sort-step v (count v)))))))