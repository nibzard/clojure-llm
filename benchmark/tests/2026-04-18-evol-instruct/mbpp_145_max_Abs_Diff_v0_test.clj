(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= #{[1 9] [9 1]} (max-abs-diff-pairs [1 5 3 9])))
  (is (= #{[4 4]} (max-abs-diff-pairs [4 4 4]))))

(run-test test-variation)
