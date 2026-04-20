(defn sum-of-squares-odd-prefix
  "Return the sum of squares of the first n odd natural numbers.

  Examples:
  (sum-of-squares-odd-prefix 1) => 1
  (sum-of-squares-odd-prefix 3) => 35
  (sum-of-squares-odd-prefix 5) => 165"
  [n]
  (transduce
    (take n)
    +
    (map #(* % %) (iterate #(+ % 2) 1))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (sum-of-squares-odd-prefix 1)))
  (is (= 35 (sum-of-squares-odd-prefix 3)))
  (is (= 165 (sum-of-squares-odd-prefix 5))))

(run-test test-variation)
