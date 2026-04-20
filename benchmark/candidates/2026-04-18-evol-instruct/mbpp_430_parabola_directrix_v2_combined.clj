(defn parabola-focus
  "Return the focus of the parabola y = ax^2 + bx + c as a 2-element vector [x y].

  Handle only numeric inputs. If a is zero, return nil since the graph is not a parabola.

  Examples:
  (parabola-focus 1 0 0) => [0 1/4]
  (parabola-focus 2 -4 1) => [1 1/8]
  (parabola-focus 0 3 2) => nil"
  [a b c]
  (when (not (zero? a))
    (let [h (/ (- b) (* 2 a))
          k (- c (/ (* b b) (* 4 a)))]
      [h (+ k (/ 1 (* 4 a)))])))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0 1/4] (parabola-focus 1 0 0)))
  (is (= [1 1/8] (parabola-focus 2 -4 1)))
  (is (= nil (parabola-focus 0 3 2))))

(run-test test-variation)
