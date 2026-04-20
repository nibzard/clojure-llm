(defn check-element-prefix
  "Return true if every item in coll matches the given prefix string.
   Treat nil as an empty collection. Works with lists, vectors, and lazy sequences.

   Examples:
   (check-element-prefix [\"cl\" \"cl\" \"cl\"] \"cl\") => true
   (check-element-prefix [\"cl\" \"clj\"] \"cl\") => false
   (check-element-prefix nil \"x\") => true"
  [coll prefix]
  (every? #(clojure.string/starts-with? % prefix) (or coll [])))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (check-element-prefix ["cl" "cl" "cl"] "cl")))
  (is (= false (check-element-prefix ["cl" "clj"] "cl")))
  (is (= true (check-element-prefix nil "x"))))

(run-test test-variation)
