(defn pair-xor-sum
  "Return the sum of XOR values for all unique unordered pairs in a collection of integers.

  Accepts any sequential collection and ignores nil values.
  Works with negative numbers using Clojure's bit-xor semantics.

  Examples:
  (pair-xor-sum [1 2 3]) ;=> 6
  (pair-xor-sum [1 nil 2]) ;=> 3"
  [xs]
  (let [vals (remove nil? xs)]
    (reduce
      (fn [acc [a b]]
        (+ acc (bit-xor a b)))
      0
      (for [a vals
            b vals
            :when (< a b)]
        [a b]))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (pair-xor-sum [1 2 3]))))

(run-test test-variation)
