(defn parabola_directrix
  "Write a function to find the directrix of a parabola."
  [a b c]
  (when (not (zero? a))
    (+ (/ (* b b) (* 4 a)) (/ c a) (/ 1 (* 4 a)))))