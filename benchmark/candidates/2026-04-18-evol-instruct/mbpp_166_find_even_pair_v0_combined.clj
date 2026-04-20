(defn count-odd-xor-pairs
  "Count the number of pairs of numbers in a collection whose bitwise xor is odd.

  Examples:
  (count-odd-xor-pairs [1 2 3 4]) => 4
  (count-odd-xor-pairs [2 4 6]) => 0
  (count-odd-xor-pairs nil) => 0

  The function should work with any sequential collection and return 0 for nil."
  [xs]
  (let [xs (or xs [])
        odds (count (filter odd? xs))
        evens (- (count xs) odds)]
    (* odds evens)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (count-odd-xor-pairs [1 2 3 4])))
  (is (= 0 (count-odd-xor-pairs [2 4 6])))
  (is (= 0 (count-odd-xor-pairs nil))))

(run-test test-variation)
