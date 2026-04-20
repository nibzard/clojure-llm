(defn min-range
  "Return the smallest difference between any two values in the given collection.

  The input may be a lazy or infinite sequence, but only the first `n` values
  passed in are considered. If there are fewer than 2 values, return nil.

  Examples:
  (min-range [3 8 1 10]) ;=> 2   ; from 1 and 3
  (min-range [5])        ;=> nil
  (min-range nil)        ;=> nil"
  [xs]
  (let [vals (sort (seq xs))]
    (when (and vals (next vals))
      (apply min (map - (rest vals) vals)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (min-range [3 8 1 10])))
  (is (= 1 (min-range [7 2 4])))
  (is (= nil (min-range [5])))
  (is (= nil (min-range nil))))

(run-test test-variation)
