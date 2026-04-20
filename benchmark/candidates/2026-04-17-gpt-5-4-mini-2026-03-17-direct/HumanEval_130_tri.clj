(defn tri
  [n]
  (let [tri-val (fn [k]
                  (if (odd? k)
                    (let [a (tri-val (dec k))
                          b (tri-val (- k 2))
                          c (tri-val (inc k))]
                      (+ a b c))
                    (+ 1 (/ k 2))))]
    (vec (map tri-val (range 1 (inc n))))))