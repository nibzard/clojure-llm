(defn sort-numbers-by-frequencies
  "Given a collection of integers, return a vector of the distinct numbers sorted by:
  1) descending frequency
  2) ascending numeric value when frequencies tie

  Return an empty vector for nil or an empty collection.

  Examples:
  (sort-numbers-by-frequencies [3 1 3 2 2 2 5])
  => [2 3 1 5]

  (sort-numbers-by-frequencies nil)
  => []
  "
  [xs]
  (->> xs
       frequencies
       (sort-by (fn [[n cnt]] [(- cnt) n]))
       (map first)
       vec))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3 1 5] (sort-numbers-by-frequencies [3 1 3 2 2 2 5])))
  (is (= [1 2 3] (sort-numbers-by-frequencies [3 1 2 3 2 1])))
  (is (= [] (sort-numbers-by-frequencies nil))))

(run-test test-variation)
