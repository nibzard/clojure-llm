(defn eulerian_num
  "Write a function to find the Eulerian number a(n, m)."
  [n m]
  (let [memo (atom {})]
    (letfn [(a [n m]
              (cond
                (or (neg? n) (neg? m) (>= m n)) 0
                (zero? m) 1
                (= n 1) 1
                :else (if-let [v (@memo [n m])]
                        v
                        (let [v (+ (* (inc m) (a (dec n) m))
                                   (* (- n m) (a (dec n) (dec m))))]
                          (swap! memo assoc [n m] v)
                          v))))]
      (a n m))))