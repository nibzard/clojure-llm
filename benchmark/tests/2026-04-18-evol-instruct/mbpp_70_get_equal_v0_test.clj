(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (same-non-nil-count? [])))
  (is (= true (same-non-nil-count? [nil nil])))
  (is (= true (same-non-nil-count? [[1 2] nil [3 4]])))
  (is (= false (same-non-nil-count? [[1] [2 3]]))))

(run-test test-variation)
