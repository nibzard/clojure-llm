(defn bitwise-xor-flags
  "Return the indices where exactly one of the two input collections is truthy.
  Works on vectors, lists, and lazy/infinite sequences; stops at the shorter input.

  Examples:
  (bitwise-xor-flags [true false true] [false false true]) ;=> [0]
  (bitwise-xor-flags [1 nil 3] [nil 2 3]) ;=> [0 1]"
  [coll1 coll2]
  (->> (map vector coll1 coll2)
       (map-indexed (fn [i [a b]]
                      (when (not= (boolean a) (boolean b))
                        i)))
       (remove nil?)
       vec))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0] (bitwise-xor-flags [true false true] [false false true])))
  (is (= [0 1] (bitwise-xor-flags [1 nil 3] [nil 2 3])))
  (is (= [1 3] (bitwise-xor-flags [0 1 0 1] [0 0 0 0]))))

(run-test test-variation)
