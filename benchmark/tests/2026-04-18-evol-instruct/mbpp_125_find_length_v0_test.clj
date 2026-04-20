(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (longest-balanced-prefix [0 1 1 0 1 0])))
  (is (= 4 (longest-balanced-prefix [0 0 1 1 1])))
  (is (= 0 (longest-balanced-prefix nil))))

(run-test test-variation)
