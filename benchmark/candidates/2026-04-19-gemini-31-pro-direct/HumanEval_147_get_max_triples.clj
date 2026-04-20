(defn get_max_triples [n]
  (let [c0 (quot (+ n 1) 3)
        c1 (- n c0)
        choose3 (fn [x]
                  (if (< x 3)
                    0
                    (quot (*' x (*' (- x 1) (- x 2))) 6)))]
    (+ (choose3 c0) (choose3 c1))))