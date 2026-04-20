(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 10 (sum-uppercase-name-lengths ["Alice" "bob" "Carol"]))))

(run-test test-variation)
