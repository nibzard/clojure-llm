(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (count-balanced-subvectors [1 0 1])))
  (is (= 0 (count-balanced-subvectors nil)))
  (is (= 1 (count-balanced-subvectors [2 -1 0]))))

(run-test test-variation)
