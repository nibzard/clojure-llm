(defn split-evenly
  "Split a collection into two vectors: items at even indexes and items at odd indexes.

  Examples:
  (split-evenly [1 2 3 4 5]) ;=> [[1 3 5] [2 4]]
  (split-evenly \"abcd\")    ;=> [[\\a \\c] [\\b \\d]]
  (split-evenly nil)         ;=> [[] []]"
  [coll]
  (let [coll (or coll [])]
    [(vec (keep-indexed (fn [i x] (when (even? i) x)) coll))
     (vec (keep-indexed (fn [i x] (when (odd? i) x)) coll))]))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3 5] [2 4]] (split-evenly [1 2 3 4 5])))
  (is (= [[\a \c] [\b \d]] (split-evenly "abcd")))
  (is (= [[] []] (split-evenly nil))))

(run-test test-variation)
