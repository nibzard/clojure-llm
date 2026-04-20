(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (diagonal-sum? [[1 2 3] [4 5 6] [7 8 9]]))))

(run-test test-variation)
