(defn has-consecutive-run?
  "Return true if the collection contains any pair of consecutive integers in the same order, or false otherwise.

  Works with any sequential collection, ignores nil values, and only checks adjacent elements.

  Examples:
  (has-consecutive-run? [1 2 5])      ;=> true
  (has-consecutive-run? [1 3 4 6])    ;=> true
  (has-consecutive-run? [1 3 5])      ;=> false
  (has-consecutive-run? nil)          ;=> false"
  [coll]
  (boolean
    (some (fn [[a b]] (= (inc a) b))
          (partition 2 1 (or coll [])))))