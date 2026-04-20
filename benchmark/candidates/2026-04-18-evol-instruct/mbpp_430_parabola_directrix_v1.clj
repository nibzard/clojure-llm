(defn parabola-focus
  "Return the focus of a parabola in the form [x y].

  The parabola is given by ax^2 + bx + c = y.

  Examples:
  (parabola-focus 1 0 0) => [0 1/4]
  (parabola-focus 2 4 1) => [-1 1/8]"
  [a b c]
  (let [h (/ (- b) (* 2 a))
        k (+ c (/ (* b b) (* 4 a)))
        p (/ 1 (* 4 a))]
    [h (+ k p)]))