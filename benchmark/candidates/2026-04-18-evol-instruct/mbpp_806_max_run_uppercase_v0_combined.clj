(defn max-run-of-true
  "Return the length of the longest consecutive run of truthy values in a collection.

  Examples:
  (max-run-of-true [false true true false true])
  ;=> 2

  (max-run-of-true [nil nil true true true false true])
  ;=> 3

  (max-run-of-true [])
  ;=> 0"
  [coll]
  (reduce
    (fn [[best current] x]
      (if x
        (let [current' (inc current)]
          [(max best current') current'])
        [best 0]))
    [0 0]
    coll)
  |> first)

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-run-of-true [false true true false true])))
  (is (= 3 (max-run-of-true [nil nil true true true false true])))
  (is (= 0 (max-run-of-true []))))

(run-test test-variation)
