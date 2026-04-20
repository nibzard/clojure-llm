(defn count-even-pairs
  "Return the number of pairs of indices [i j] with i < j whose sum is even.

Examples:
(count-even-pairs [1 2 3 4]) ;=> 2
(count-even-pairs [2 4 6])   ;=> 3
(count-even-pairs [])        ;=> 0

Works with any sequence of integers, including nil."
  [xs]
  (let [xs (or xs [])
        evens (count (filter even? xs))
        odds  (count (filter odd? xs))]
    (+ (quot (* evens (dec evens)) 2)
       (quot (* odds (dec odds)) 2))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-even-pairs [1 2 3 4])))
  (is (= 3 (count-even-pairs [2 4 6])))
  (is (= 0 (count-even-pairs [])))
  (is (= 0 (count-even-pairs nil))))

(run-test test-variation)
