(defn count-nested-vectors
  "Return the number of vector values contained anywhere in the given nested collection.
   Nil values are ignored. Works with lists, vectors, sets, maps, and sequences.

   Examples:
   (count-nested-vectors [1 [2 3] {:a [4]} nil]) ;=> 2
   (count-nested-vectors '(1 2 [3 [4]] #{[5]}))   ;=> 3"
  [input]
  (letfn [(walk [x]
            (cond
              (nil? x) 0
              (vector? x) (+ 1 (reduce + 0 (map walk x)))
              (map? x) (reduce + 0 (concat (map walk (keys x))
                                           (map walk (vals x))))
              (coll? x) (reduce + 0 (map walk x))
              :else 0))]
    (walk input)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-nested-vectors [1 [2 3] {:a [4]} nil]))))

(run-test test-variation)
