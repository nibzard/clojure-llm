(defn unique-in-order
  "Return a lazy sequence of the items from coll with consecutive duplicates removed.

  Works on any sequential collection, preserves the first item of each run,
  and returns nil for nil input.

  Examples:
  (unique-in-order [1 1 2 2 3 1 1]) ;=> (1 2 3 1)
  (unique-in-order \"aaabcca\")      ;=> (\\a \\b \\c \\a)
  (unique-in-order nil)             ;=> nil"
  [coll]
  (when coll
    (lazy-seq
      (let [s (seq coll)]
        (when s
          (cons (first s)
                (unique-in-order (drop-while #(= % (first s)) (rest s)))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [\a \b \c \a] (vec (unique-in-order "aaabcca")))))

(run-test test-variation)
