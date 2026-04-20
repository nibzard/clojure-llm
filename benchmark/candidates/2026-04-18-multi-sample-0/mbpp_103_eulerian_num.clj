(defn eulerian_num
  "	Write a function to find the Eulerian number a(n, m)."
  [n m]
  (letfn [(eulerian [n m]
            (cond
              (or (nil? n) (nil? m)) nil
              (neg? n) 0
              (neg? m) 0
              (>= m n) 0
              (zero? n) (if (zero? m) 1 0)
              :else (+ (* (- n m) (eulerian (dec n) (dec m)))
                       (* (inc m) (eulerian (dec n) m)))))]
    (eulerian n m)))