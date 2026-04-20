(defn parabola_directrix
  "	Write a function to find the directrix of a parabola."
  [a b c]
  (let [x (- (/ b (* 2 a)))
        y (- (/ (+ (* b b) (* 4 a c)) (* 4 a)))]
    y))