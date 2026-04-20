(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (even-index-sum [1 2 3 4 5]))))

(run-test test-variation)
