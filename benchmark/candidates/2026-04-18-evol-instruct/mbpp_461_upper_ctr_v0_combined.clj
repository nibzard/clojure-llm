(defn even-index-sum
  "Return the sum of the values at even indexes in a vector.

  Examples:
  (even-index-sum [1 2 3 4 5]) ;=> 9
  (even-index-sum []) ;=> 0
  (even-index-sum [10]) ;=> 10"
  [v]
  (reduce + (map second (filter #(even? (first %)) (map-indexed vector v)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (even-index-sum [1 2 3 4 5]))))

(run-test test-variation)
