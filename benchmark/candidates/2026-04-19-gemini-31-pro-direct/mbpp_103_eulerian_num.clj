(defn eulerian_num [n m]
  (cond
    (= m 0) 1
    (or (< m 0) (>= m n)) 0
    :else (+ (* (- n m) (eulerian_num (dec n) (dec m)))
             (* (inc m) (eulerian_num (dec n) m)))))