(defn nth-nonconsecutive
  "Return the nth nonconsecutive number (0-indexed) represented as a sum of nonconsecutive Fibonacci numbers.

  The sequence starts:
  0 -> 0
  1 -> 1
  2 -> 2
  3 -> 4
  4 -> 5
  5 -> 7

  Examples:
  (nth-nonconsecutive 0) => 0
  (nth-nonconsecutive 5) => 7

  Handle nil by returning nil."
  [n]
  (when (some? n)
    (let [fib (take-while #(<= % n)
                          (map first
                               (iterate (fn [[a b]] [b (+ a b)]) [1 2])))
          bits (Integer/toBinaryString (inc n))]
      (reduce + (map #(* %1 %2)
                     fib
                     (reverse (map #(Character/digit ^char % 2) bits)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (nth-nonconsecutive 0)))
  (is (= 1 (nth-nonconsecutive 1)))
  (is (= 2 (nth-nonconsecutive 2)))
  (is (= 4 (nth-nonconsecutive 3)))
  (is (= 7 (nth-nonconsecutive 5)))
  (is (= nil (nth-nonconsecutive nil))))

(run-test test-variation)
