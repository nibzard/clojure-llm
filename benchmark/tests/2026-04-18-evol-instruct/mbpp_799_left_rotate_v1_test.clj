(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3221225475 (rotate-right 13 2))))

(run-test test-variation)
