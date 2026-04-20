(defn sum-deep
  "Return the sum of all numeric values in a nested collection.

  The input may contain vectors, lists, sets, maps, strings, nils, and numbers.
  Only numeric values should be counted; everything else is ignored.

  Examples:
  (sum-deep [1 [2 nil] {:a 3} \"x\" #{4 5}]) ;=> 15
  (sum-deep nil) ;=> 0
  (sum-deep [1 :a \"2\" [3.5]]) ;=> 4.5"
  [data]
  (letfn [(walk [x]
            (cond
              (number? x) x
              (map? x)    (reduce + 0 (concat (keys x) (vals x)))
              (coll? x)   (reduce + 0 (map walk x))
              :else       0))]
    (walk data)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 15 (sum-deep [1 [2 nil] {:a 3} "x" #{4 5}])))
  (is (= 0 (sum-deep nil)))
  (is (= 4.5 (sum-deep [1 :a "2" [3.5]]))))

(run-test test-variation)
