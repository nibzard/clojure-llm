(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [-4 -3 5] (maximum-unique [-3 -4 5] 3)))
  (is (= [-4 4] (maximum-unique [4 -4 4] 2)))
  (is (= [1 2 -1] (maximum-unique [-3 2 1 2 -1 -2 1] 3))))

(run-test test-variation)
