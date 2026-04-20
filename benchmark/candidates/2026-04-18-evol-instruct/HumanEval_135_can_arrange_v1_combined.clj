(defn first-disorder-index
  "Return the index of the first element in the collection that is less than
  or equal to the element immediately before it.

  Return -1 if the collection is already strictly increasing or has fewer
  than 2 items.

  Works on any sequential collection.

  Examples:
  >>> (first-disorder-index [1 2 4 3 5])
  3
  >>> (first-disorder-index '(1 2 3))
  -1
  >>> (first-disorder-index [10])
  -1"
  [coll]
  (let [res (first (keep-indexed (fn [i [a b]]
                                   (when (<= b a) (inc i)))
                                 (partition 2 1 coll)))]
    (or res -1)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (first-disorder-index [1 2 4 3 5])))
  (is (= -1 (first-disorder-index [1 2 3])))
  (is (= -1 (first-disorder-index [10])))
  (is (= 2 (first-disorder-index '(1 3 2 4)))))

(run-test test-variation)
