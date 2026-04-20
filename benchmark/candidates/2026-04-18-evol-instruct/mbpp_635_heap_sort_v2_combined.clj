(defn stable-sort-by
  "Return a stable sort of items by the given key function.

Examples:
(stable-sort-by count [\"bbb\" \"a\" \"cc\"]) ;=> [\"a\" \"cc\" \"bbb\"]
(stable-sort-by :age [{:name \"Ada\" :age 30} {:name \"Bob\" :age 25} {:name \"Eve\" :age 30}])
;=> [{:name \"Bob\" :age 25} {:name \"Ada\" :age 30} {:name \"Eve\" :age 30}]

Nil values are allowed and are sorted before non-nil values."
  [key-fn coll]
  (sort-by (fn [x] [(if (nil? (key-fn x)) 0 1) (key-fn x)]) coll))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [nil nil 1 2 3] (stable-sort-by identity [3 nil 2 nil 1])))
  (is (= ["a" "cc" "bbb"] (stable-sort-by count ["bbb" "a" "cc"])))
  (is (= [{:name "Bob" :age 25} {:name "Ada" :age 30} {:name "Eve" :age 30}]
         (stable-sort-by :age [{:name "Ada" :age 30} {:name "Bob" :age 25} {:name "Eve" :age 30}])))

(run-test test-variation)
