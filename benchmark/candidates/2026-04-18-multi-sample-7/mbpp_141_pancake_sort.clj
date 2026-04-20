(defn pancake_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(flip-prefix [v k]
            (into (vec (rseq (subvec v 0 k))) (subvec v k)))
          (max-index [v n]
            (first
             (reduce (fn [[best-i best-val] i]
                       (let [x (v i)]
                         (if (> (compare x best-val) 0)
                           [i x]
                           [best-i best-val])))
                     [0 (v 0)]
                     (range 1 n))))
          (sort-step [v n]
            (if (<= n 1)
              v
              (let [mi (max-index v n)]
                (cond
                  (= mi (dec n)) (recur v (dec n))
                  (zero? mi) (recur (flip-prefix v n) (dec n))
                  :else (recur (flip-prefix (flip-prefix v (inc mi)) n) (dec n))))))]
    (cond
      (nil? nums) nil
      :else (let [v (vec nums)]
              (into (empty nums) (sort-step v (count v)))))))