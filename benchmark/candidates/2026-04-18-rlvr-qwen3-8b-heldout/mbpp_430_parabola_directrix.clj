(defn parabola_directrix
  "	Write a function to find the directrix of a parabola."
  [a b c]
  (when (not (zero? a))
    (let [x (- (/ b (* 2 a)))
          y (- (+ (* 1/4 a (* b b)) (* -1 a c)) (/ 1 (* 4 a)))]
      (str "y = " y))))