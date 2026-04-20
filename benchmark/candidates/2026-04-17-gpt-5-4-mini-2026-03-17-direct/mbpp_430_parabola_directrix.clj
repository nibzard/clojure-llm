(defn parabola_directrix
  "	Write a function to find the directrix of a parabola."
  [a b c]
  (let [a (double a)
        b (double b)
        c (double c)]
    (if (zero? a)
      nil
      (let [h (/ (- b) (* 2.0 a))
            k (- c (/ (* b b) (* 4.0 a)))
            p (/ 1.0 (* 4.0 a))]
        (+ k (- p)))))