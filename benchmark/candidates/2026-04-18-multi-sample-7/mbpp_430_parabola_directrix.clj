(defn parabola_directrix
  "	Write a function to find the directrix of a parabola."
  [a b c]
  (let [h (- (/ b (* 2 a)))
        k (- c (/ (* b b) (* 4 a)))]
    (- k (/ 1.0 (* 4 a)))))