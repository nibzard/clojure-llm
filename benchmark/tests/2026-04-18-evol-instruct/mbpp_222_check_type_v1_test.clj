(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3 5] [2 4]] (stable-partition odd? [1 2 3 4 5])))
  (is (= [["a" "b"] [1 nil]] (stable-partition string? ["a" 1 nil "b"])))
  (is (= [[] []] (stable-partition even? nil))))

(run-test test-variation)
