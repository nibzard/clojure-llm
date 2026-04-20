(defn parabola-focus
  "Return the focus of the parabola y = ax^2 + bx + c as [x y].

  Examples:
  (parabola-focus 1 0 0) => [0 1/4]
  (parabola-focus 2 4 1) => [-1 0]
  "
  [a b c]
  (let [x0 (/ (- b) (* 2 a))
        y0 (- c (/ (* b b) (* 4 a)))]
    [x0 (+ y0 (/ 1 (* 4 a)))]))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0 1/4] (parabola-focus 1 0 0)))
  (is (= [-1 0] (parabola-focus 2 4 1))))

(run-test test-variation)
