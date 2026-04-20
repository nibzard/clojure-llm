(defn nested-product
  "Return the product of all numeric values found in a nested collection.

  The input may contain lists, vectors, sets, and maps. For maps, multiply both keys and values.
  Ignore nil values. If no numeric values are found, return 1.

  Examples:
  (nested-product [1 [2 nil] {:a 3}]) ;=> 6
  (nested-product {:x 2, :y [3 4]})   ;=> 24
  (nested-product [])                ;=> 1"
  [data]
  (letfn [(walk [x]
            (cond
              (nil? x) 1
              (number? x) x
              (map? x) (reduce * 1 (map walk (concat (keys x) (vals x))))
              (coll? x) (reduce * 1 (map walk x))
              :else 1))]
    (walk data)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (nested-product [1 [2 nil] {:a 3}])))
  (is (= 24 (nested-product {:x 2, :y [3 4]})))
  (is (= 1 (nested-product []))))

(run-test test-variation)
