(defn parabola_directrix
  "	Write a function to find the directrix of a parabola."
  [a b c]
  (if (zero? a)
    nil
    (/ (+ (* b b) (- (* 4 a c)) 1) (* 4 a))))