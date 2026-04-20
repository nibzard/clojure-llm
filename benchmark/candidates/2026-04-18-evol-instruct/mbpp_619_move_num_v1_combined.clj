(defn move-num
  "Return a vector with all non-numeric elements first, followed by all numeric elements, preserving their original order.

  Works on any sequential collection.

  Examples:
  (move-num [1 :a 2 \"x\" 3]) => [:a \"x\" 1 2 3]
  (move-num '(\"a\" 4 nil 2 :b)) => (\"a\" nil :b 4 2)"
  [coll]
  (vec (concat (remove number? coll)
               (filter number? coll))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:a "x" 1 2 3] (move-num [1 :a 2 "x" 3]))))

(run-test test-variation)
