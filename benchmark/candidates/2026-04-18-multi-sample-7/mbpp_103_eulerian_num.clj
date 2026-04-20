(defn eulerian_num
  "	Write a function to find the Eulerian number a(n, m)."
  [n m]
  (letfn [(euler [n m]
            (cond
              (or (nil? n) (nil? m)
                  (neg? n) (neg? m)
                  (>= m n)) 0
              (zero? n) 1
              :else (+ (* (- n m) (euler (dec n) (dec m)))
                       (* (inc m) (euler (dec n) m)))))]
    (euler n m)))