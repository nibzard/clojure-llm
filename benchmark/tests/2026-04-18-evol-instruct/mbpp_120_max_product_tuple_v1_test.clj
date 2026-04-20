(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (max-pair-sum [1 5 2 4]))))

(run-test test-variation)
