(defn count-out-of-order
  "Count the number of out-of-order pairs in a collection.

  An out-of-order pair is any pair of indices i < j such that:
  - the value at i is greater than the value at j

  The input may be any sequential collection of numbers (not just vectors).
  Return 0 for nil or empty input.

  Examples:
  (count-out-of-order [2 4 1 3 5]) ;=> 3
  (count-out-of-order '(1 2 3))    ;=> 0
  (count-out-of-order nil)         ;=> 0"
  [xs]
  (let [v (vec (or xs []))]
    (loop [i 0
           cnt 0]
      (if (< i (count v))
        (recur (inc i)
               (+ cnt (count (filter #(> (v i) %) (subvec v (inc i)))) ))
        cnt))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-out-of-order [2 4 1 3 5]))))

(run-test test-variation)
