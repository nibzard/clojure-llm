(defn alternating-sum
  "Return the alternating sum of the first n natural numbers: 1 - 2 + 3 - 4 + ...
  
  Examples:
  (alternating-sum 5) => 3
  (alternating-sum 6) => -3
  
  For n <= 0, return 0."
  [n]
  (reduce + (map * (range 1 (inc n)) (cycle [1 -1]))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (alternating-sum 5))))

(run-test test-variation)
