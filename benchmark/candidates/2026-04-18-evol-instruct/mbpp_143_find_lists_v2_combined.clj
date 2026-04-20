(defn count-nested-seqs
  "Return the number of nested sequential collections inside `coll`, excluding `coll` itself.

  Treat lists, vectors, and lazy sequences as sequential collections.
  Ignore maps, sets, strings, and nil.

  Examples:
  (count-nested-seqs [1 [2 3] '(4 [5])])
  ;; => 3

  (count-nested-seqs '((1 2) 3 [4 (5 6)]))
  ;; => 4"
  [coll]
  (let [seq?* (fn [x]
                (and (sequential? x)
                     (not (string? x))))]
    (count (filter seq?* (tree-seq seq?* seq coll)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-nested-seqs [1 [2 3] '(4 [5])]))))

(run-test test-variation)
