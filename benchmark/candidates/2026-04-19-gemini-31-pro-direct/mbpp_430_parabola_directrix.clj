(defn parabola_directrix
  "Write a function to find the directrix of a parabola."
  [a b c]
  (- c (/ (inc (* b b)) (* 4 a))))