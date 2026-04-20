(defn stable-sort-by-length
  "Return the input strings sorted by length in ascending order, preserving the
  original order of strings with the same length.

  Examples:
  (stable-sort-by-length [\"bb\" \"a\" \"cc\" \"ddd\" \"ee\"])
  => [\"a\" \"bb\" \"cc\" \"ee\" \"ddd\"]

  (stable-sort-by-length nil)
  => []"
  [xs]
  (->> (or xs [])
       (sort-by count)
       vec))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["a" "bb" "cc" "ee" "ddd"]
         (stable-sort-by-length ["bb" "a" "cc" "ddd" "ee"]))))

(run-test test-variation)
