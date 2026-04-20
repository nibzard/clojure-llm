(defn parabola_directrix
  "	Write a function to find the directrix of a parabola."
  [a b c]
  (if (zero? a)
    nil
    (/ (- b b (* 1.0 1.0)) (* 4.0 a))))