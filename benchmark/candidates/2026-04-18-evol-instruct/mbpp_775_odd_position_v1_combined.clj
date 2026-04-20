(defn odd-position-sum?
  "Return true if every value at an odd index is odd.
  Uses zero-based indexing: index 1, 3, 5, ... must contain odd numbers.

  Examples:
  (odd-position-sum? [2 3 4 5]) => true
  (odd-position-sum? [1 2 3 4]) => false
  (odd-position-sum? nil) => true"
  [nums]
  (every? odd? (keep-indexed (fn [i n] (when (odd? i) n)) nums)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (odd-position-sum? [2 3 4 5])))
  (is (= false (odd-position-sum? [1 2 3 4])))
  (is (= true (odd-position-sum? nil))))

(run-test test-variation)
