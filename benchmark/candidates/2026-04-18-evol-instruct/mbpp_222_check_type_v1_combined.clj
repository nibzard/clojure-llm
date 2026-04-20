(defn stable-partition
  "Return a vector of two vectors: [matches non-matches].

  Keep the original order of elements in each group.

  The predicate may be any boolean function. If the input collection is nil, return [[] []].

  Examples:
  (stable-partition odd? [1 2 3 4 5])  ;; => [[1 3 5] [2 4]]
  (stable-partition string? [\"a\" 1 nil \"b\"]) ;; => [[\"a\" \"b\"] [1 nil]]
  (stable-partition even? nil) ;; => [[] []]"
  [pred coll]
  (if (nil? coll)
    [[] []]
    (reduce (fn [[yes no] x]
              (if (pred x)
                [(conj yes x) no]
                [yes (conj no x)]))
            [[] []]
            coll)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3 5] [2 4]] (stable-partition odd? [1 2 3 4 5])))
  (is (= [["a" "b"] [1 nil]] (stable-partition string? ["a" 1 nil "b"])))
  (is (= [[] []] (stable-partition even? nil))))

(run-test test-variation)
