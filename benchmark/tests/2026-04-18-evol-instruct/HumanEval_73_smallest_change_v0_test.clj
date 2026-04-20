(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 5 (max-pair-sum [1 2 3 4]))))

(run-test test-variation)
