(defn first-missing-positive
  "Return the smallest missing positive integer from an unsorted collection.

  Works with vectors, lists, lazy sequences, and nil. Non-positive values are ignored.

  Examples:
  (first-missing-positive [3 4 -1 1]) => 2
  (first-missing-positive [1 2 0]) => 3
  (first-missing-positive nil) => 1"
  [coll]
  (let [s (into #{} (filter pos? (or coll [])))]
    (loop [n 1]
      (if (contains? s n)
        (recur (inc n))
        n))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (first-missing-positive [3 4 -1 1])))
  (is (= 3 (first-missing-positive [1 2 0])))
  (is (= 1 (first-missing-positive nil)))
  (is (= 4 (first-missing-positive (range 1 4)))))

(run-test test-variation)
