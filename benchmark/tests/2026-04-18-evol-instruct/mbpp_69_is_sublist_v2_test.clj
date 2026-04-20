(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (contains-subsequence? [1 2 3 4] [2 3]))))

(run-test test-variation)
