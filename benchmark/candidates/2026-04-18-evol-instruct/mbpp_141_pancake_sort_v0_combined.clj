(defn stable_by
  "Return a stable sequence of items sorted by the value returned from `keyfn`.

  Stability means that items with equal keys keep their original relative order.

  Examples:
  (stable_by count [\"aa\" \"b\" \"cc\" \"d\"])
  ;; => (\"b\" \"d\" \"aa\" \"cc\")

  (stable_by :age [{:name \"Ann\" :age 30}
                   {:name \"Bob\" :age 20}
                   {:name \"Cat\" :age 30}])
  ;; => ({:name \"Bob\" :age 20}
  ;;     {:name \"Ann\" :age 30}
  ;;     {:name \"Cat\" :age 30})"
  [keyfn coll]
  (sort-by keyfn coll))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("b" "d" "aa" "cc")
         (stable_by count ["aa" "b" "cc" "d"]))))

(run-test test-variation)
