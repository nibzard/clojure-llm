(defn get_odd_collatz
  [n]
  (let [collatz-seq (loop [current n
                           seq [current]]
                      (if (= current 1)
                        seq
                        (let [next (if (even? current)
                                     (/ current 2)
                                     (+ (* 3 current) 1))]
                          (recur next (conj seq next)))))]
    (vec (sort (filter odd? collatz-seq)))))