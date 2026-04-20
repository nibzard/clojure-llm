(defn stable-sort-by
  "Return a stable sorted sequence of the items in `coll`, ordered by the value
  produced by `keyfn`.

  Examples:
  (stable-sort-by count [\"bb\" \"a\" \"ccc\" \"dd\"])
  ;; => (\"a\" \"bb\" \"dd\" \"ccc\")

  (stable-sort-by :age [{:name \"A\" :age 2}
                        {:name \"B\" :age 1}
                        {:name \"C\" :age 2}])
  ;; => ({:name \"B\" :age 1} {:name \"A\" :age 2} {:name \"C\" :age 2})

  If `coll` is nil, return an empty sequence."
  [keyfn coll]
  (if (nil? coll)
    '()
    (sort-by keyfn coll)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("a" "bb" "dd" "ccc")
         (stable-sort-by count ["bb" "a" "ccc" "dd"])))
  (is (= '({:name "B" :age 1} {:name "A" :age 2} {:name "C" :age 2})
         (stable-sort-by :age [{:name "A" :age 2}
                               {:name "B" :age 1}
                               {:name "C" :age 2}])))
  (is (= '() (stable-sort-by identity nil))))

(run-test test-variation)
