(defn can-be-expressed-as-sum-of-four-odds
  "Return true if n can be written as the sum of exactly 4 positive odd numbers, false otherwise.

  Examples:
  (can-be-expressed-as-sum-of-four-odds 4)   => false
  (can-be-expressed-as-sum-of-four-odds 8)   => true
  (can-be-expressed-as-sum-of-four-odds 16)  => true
  (can-be-expressed-as-sum-of-four-odds 18)  => false"
  [n]
  (and (integer? n)
       (>= n 4)
       (even? n)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= false (can-be-expressed-as-sum-of-four-odds 4)))
  (is (= true (can-be-expressed-as-sum-of-four-odds 8)))
  (is (= true (can-be-expressed-as-sum-of-four-odds 16))))

(run-test test-variation)
