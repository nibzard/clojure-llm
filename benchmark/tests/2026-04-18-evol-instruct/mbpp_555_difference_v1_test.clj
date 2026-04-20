(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 22 (difference 3)))
  (is (= 2640 (difference 10)))
  (is (= 0 (difference 0))))

(run-test test-variation)
