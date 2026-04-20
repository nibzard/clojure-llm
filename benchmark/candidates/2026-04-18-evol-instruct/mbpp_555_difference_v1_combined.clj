(defn difference
  "Return the difference between the sum of the squares of the first n natural numbers and the square of their sum.

  Use an efficient formula, and support n = 0.

  Examples:
  (difference 3) ;=> 22
  (difference 10) ;=> 2640
  (difference 0) ;=> 0"
  [n]
  (let [sum (*' n (/ (inc n) 2))
        sum-sq (*' sum sum)
        sq-sum (/ (*' n (inc n) (+' 1 (*' 2 n))) 6)]
    (- sum-sq sq-sum)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 22 (difference 3)))
  (is (= 2640 (difference 10)))
  (is (= 0 (difference 0))))

(run-test test-variation)
