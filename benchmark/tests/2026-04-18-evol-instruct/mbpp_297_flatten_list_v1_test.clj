(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 2 3 4 5) (deep-flatten [1 [2 nil] '(3 [4 5])])))
  (is (= '() (deep-flatten nil))))

(run-test test-variation)
