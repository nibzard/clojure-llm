(defn tri
  "Return a list of the first n + 1 numbers of the Tribonacci sequence."
  [n]
  (let [limit (long n)]
    (loop [i 0
           acc {0 1}]
      (if (> i limit)
        (vec (map (partial get acc) (range 0 (inc limit))))
        (let [val (if (odd? i)
                    (+ (get acc (dec i))
                       (get acc (- i 2) 0)
                       (get acc (inc i) 0))
                    (inc (quot i 2)))]
          (recur (inc i) (assoc acc i val)))))))