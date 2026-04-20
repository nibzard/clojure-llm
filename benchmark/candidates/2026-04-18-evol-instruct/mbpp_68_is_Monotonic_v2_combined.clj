(defn is_strictly_monotonic
  "Return true if the collection is strictly monotonic (entirely increasing or entirely decreasing).
  A collection with fewer than 2 elements is considered strictly monotonic.

  Examples:
  (is_strictly_monotonic [1 2 3])   ;=> true
  (is_strictly_monotonic [3 2 1])   ;=> true
  (is_strictly_monotonic [1 1 2])   ;=> false
  (is_strictly_monotonic nil)      ;=> true"
  [xs]
  (let [xs (or xs [])]
    (or (apply < xs)
        (apply > xs)
        (< (count xs) 2))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_strictly_monotonic [1 2 3 4])))
  (is (= true (is_strictly_monotonic [4 3 2 1])))
  (is (= false (is_strictly_monotonic [1 1 2])))
  (is (= true (is_strictly_monotonic [])))
  (is (= true (is_strictly_monotonic nil))))

(run-test test-variation)
