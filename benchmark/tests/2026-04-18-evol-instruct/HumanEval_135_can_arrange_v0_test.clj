(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (first_nonincreasing-index [1 2 4 3 5])))
  (is (= -1 (first_nonincreasing-index [1 2 3])))
  (is (= -1 (first_nonincreasing-index [10]))))

(run-test test-variation)
