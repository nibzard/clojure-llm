(defn split-odds
  "Return a lazy sequence of the odd numbers from `coll`.

  Works with any seqable collection, including infinite sequences.
  Preserves order and ignores nil values.

  Examples:
  (split-odds [1 2 3 4 5])   ;=> (1 3 5)
  (split-odds [nil 7 8 9])   ;=> (7 9)
  (take 4 (split-odds (range))) ;=> (1 3 5 7)"
  [coll]
  (filter odd? coll))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 3 5) (split-odds [1 2 3 4 5])))
  (is (= '(7 9) (split-odds [nil 7 8 9])))
  (is (= '(1 3 5 7) (take 4 (split-odds (range))))))

(run-test test-variation)
