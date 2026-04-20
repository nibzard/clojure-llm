(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (all-satisfy? even? [2 4 6])))
  (is (= false (all-satisfy? pos? [1 2 0])))
  (is (= true (all-satisfy? string? nil))))

(run-test test-variation)
