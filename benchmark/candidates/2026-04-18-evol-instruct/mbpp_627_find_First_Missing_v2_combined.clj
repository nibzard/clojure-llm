(defn first-missing-positive
  "Return the smallest missing positive integer from a collection of numbers.

  The input may be unsorted and may contain duplicates, nils, or non-positive values.
  Works with any seqable collection.

  Examples:
  (first-missing-positive [3 4 -1 1]) ;=> 2
  (first-missing-positive [1 2 0])    ;=> 3
  (first-missing-positive [7 8 9])    ;=> 1"
  [xs]
  (let [s (->> xs
               (filter number?)
               (filter pos?)
               set)]
    (loop [n 1]
      (if (contains? s n)
        (recur (inc n))
        n))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (first-missing-positive [3 4 -1 1])))
  (is (= 3 (first-missing-positive [1 2 0])))
  (is (= 1 (first-missing-positive [7 8 9])))
  (is (= 4 (first-missing-positive [1 2 3 3 nil -5 "x"]))))

(run-test test-variation)
