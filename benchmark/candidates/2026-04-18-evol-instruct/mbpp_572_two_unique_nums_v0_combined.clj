(defn unique-preserve-order
  "Return a vector of the unique numbers from nums, preserving the order of first appearance.

  Examples:
  (unique-preserve-order [3 1 3 2 1 4])
  => [3 1 2 4]

  (unique-preserve-order nil)
  => []"
  [nums]
  (vec (distinct (or nums []))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 1 2 4] (unique-preserve-order [3 1 3 2 1 4]))))

(run-test test-variation)
