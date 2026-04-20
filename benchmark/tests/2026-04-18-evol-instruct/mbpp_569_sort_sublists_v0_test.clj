(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 2 3] [7 9] nil [4]]
         (sort-subvectors [[3 1 2] [9 7] nil [4]]))))

(run-test test-variation)
