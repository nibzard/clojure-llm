(defn sort-by-second-desc
  "Return the vectors sorted by their second element in descending order.

  Examples:
  (sort-by-second-desc [[\"a\" 2] [\"b\" 5] [\"c\" 1]])
  => [[\"b\" 5] [\"a\" 2] [\"c\" 1]]

  Works with any collection of 2+ element vectors where the second value is comparable."
  [items]
  (sort-by second > items))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [["b" 5] ["a" 2] ["c" 1]]
         (sort-by-second-desc [["a" 2] ["b" 5] ["c" 1]]))))

(run-test test-variation)
