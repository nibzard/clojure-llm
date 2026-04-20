(defn parabola_directrix
  "	Write a function to find the directrix of a parabola."
  [a b c]
  (when (and a (not (zero? a)))
    (let [h (- (/ b (* 2 a)))
          k (- c (/ (* b b) (* 4 a)))
          p (/ 1 (* 4 a))]
      (- k p))))