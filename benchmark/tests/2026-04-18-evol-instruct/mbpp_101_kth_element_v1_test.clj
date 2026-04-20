(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (kth-present [nil 1 nil 2 3] 2))))

(run-test test-variation)
