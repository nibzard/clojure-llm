(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is-monotonic? []))))

(run-test test-variation)
