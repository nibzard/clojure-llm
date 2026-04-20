(defn deep-flatten
  "Return a flattened sequence of all non-collection values in a nested structure.

  Works on lists, vectors, and lazy sequences. Nil values are ignored.

  Examples:
  (deep-flatten [1 [2 nil] '(3 [4 5])])
  => (1 2 3 4 5)

  (deep-flatten nil)
  => ()"
  [x]
  (letfn [(step [v]
            (lazy-seq
              (cond
                (nil? v) ()
                (sequential? v) (mapcat step v)
                :else (list v))))]
    (step x)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 2 3 4 5) (deep-flatten [1 [2 nil] '(3 [4 5])])))
  (is (= '() (deep-flatten nil))))

(run-test test-variation)
