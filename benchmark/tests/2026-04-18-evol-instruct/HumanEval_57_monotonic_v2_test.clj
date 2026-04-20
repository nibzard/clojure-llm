(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (monotonic-by count ["a" "bb" "ccc"]))))

(run-test test-variation)
