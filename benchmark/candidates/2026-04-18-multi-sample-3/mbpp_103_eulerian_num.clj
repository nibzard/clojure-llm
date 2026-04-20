(defn eulerian_num
  "	Write a function to find the Eulerian number a(n, m)."
  [n m]
  (let [n (long n)
        m (long m)]
    (cond
      (neg? n) 0
      (neg? m) 0
      (>= m n) 0
      (zero? n) (if (zero? m) 1 0)
      :else
      (letfn [(euler [n m]
                (cond
                  (neg? m) 0
                  (>= m n) 0
                  (and (= n 1) (zero? m)) 1
                  :else
                  (+ (* (- n m) (euler (dec n) (dec m)))
                     (* (inc m) (euler (dec n) m)))))]
        (euler n m))))