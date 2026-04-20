(defn issorted?
  "Return true if the collection is sorted in nondecreasing order, false otherwise.

  Works with any sequential collection of comparable values, and treats nil or
  empty collections as sorted.

  Examples:
  (issorted? [1 2 2 5])  ;=> true
  (issorted? [3 1 2])    ;=> false
  (issorted? nil)        ;=> true"
  [coll]
  (if (or (nil? coll) (empty? coll))
    true
    (every? (fn [[a b]] (<= a b))
            (partition 2 1 coll))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (issorted? nil)))
  (is (= true (issorted? [])))
  (is (= true (issorted? [1 2 2 5])))
  (is (= false (issorted? [3 1 2]))))

(run-test test-variation)
