(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (count-subvectors [1 2 3]))))

(run-test test-variation)
