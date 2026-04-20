(defn rectangle-perimeter
  "Return the perimeter of a rectangle given a collection of two side lengths.

  Examples:
  (rectangle-perimeter [3 4]) ;=> 14
  (rectangle-perimeter '(5 2)) ;=> 14

  If the input is nil or does not contain exactly two numeric values, return 0."
  [dims]
  (if (and (sequential? dims)
           (= 2 (count dims))
           (every? number? dims))
    (* 2 (+ (first dims) (second dims)))
    0))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 14 (rectangle-perimeter [3 4])))
  (is (= 14 (rectangle-perimeter '(5 2))))
  (is (= 0 (rectangle-perimeter nil))))

(run-test test-variation)
