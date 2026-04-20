(defn get_max_triples [n]
  (let [count-0 (quot (+ n 1) 3)
        count-1 (- n count-0)
        comb3 (fn [c]
                (if (>= c 3)
                  (/ (* c (- c 1) (- c 2)) 6)
                  0))]
    (+ (comb3 count-0)
       (comb3 count-1))))