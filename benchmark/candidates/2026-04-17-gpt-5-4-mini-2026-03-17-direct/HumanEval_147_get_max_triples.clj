(defn get_max_triples
  [n]
  (let [r (mod n 3)
        q (quot n 3)
        counts (case r
                 0 [q q q]
                 1 [(inc q) q q]
                 2 [(inc q) (inc q) q])
        [c0 c1 c2] counts
        choose3 (fn [x] (if (>= x 3) (/ (* x (dec x) (- x 2)) 6) 0))
        choose2 (fn [x] (if (>= x 2) (/ (* x (dec x)) 2) 0))]
    (+ (choose3 c0)
       (choose3 c1)
       (choose3 c2)
       (* c0 c1 c2))))