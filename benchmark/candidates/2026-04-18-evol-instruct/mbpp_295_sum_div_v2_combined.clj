(defn sum-proper-divisors
  "Return the sum of all proper divisors of n.
Proper divisors are positive divisors less than n.

Examples:
(sum-proper-divisors 1) => 0
(sum-proper-divisors 6) => 6
(sum-proper-divisors 28) => 28"
  [n]
  (cond
    (nil? n) 0
    (<= n 1) 0
    :else
    (reduce
      +
      (filter #(zero? (mod n %))
              (range 1 n)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (sum-proper-divisors 6))))

(run-test test-variation)
