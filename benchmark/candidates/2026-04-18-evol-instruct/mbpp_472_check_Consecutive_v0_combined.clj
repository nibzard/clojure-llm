(defn consecutive-runs
  "Return the lengths of consecutive runs of equal values in the input collection.

  Works on any sequential collection, including strings and vectors.

  Examples:
  (consecutive-runs [1 1 2 2 2 3]) => [2 3 1]
  (consecutive-runs \"aaabb\") => [3 2]
  (consecutive-runs []) => []
  (consecutive-runs nil) => []"
  [coll]
  (if (seq coll)
    (mapv count (partition-by identity coll))
    []))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3 1] (consecutive-runs [1 1 2 2 2 3])))
  (is (= [3 2] (consecutive-runs "aaabb")))
  (is (= [] (consecutive-runs [])))
  (is (= [] (consecutive-runs nil))))

(run-test test-variation)
