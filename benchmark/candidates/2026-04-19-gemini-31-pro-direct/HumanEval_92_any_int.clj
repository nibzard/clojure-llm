(defn any_int [x y z]
  (and (int? x)
       (int? y)
       (int? z)
       (or (= x (+ y z))
           (= y (+ x z))
           (= z (+ x y)))))