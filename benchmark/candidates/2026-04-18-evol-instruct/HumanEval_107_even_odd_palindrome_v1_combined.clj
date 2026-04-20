(defn sum-prefix-palindromes
  "Given a positive integer n, return a vector [count sum] where:
   - count is the number of palindromic integers in the range 1..n inclusive
   - sum is the sum of all palindromic integers in that range

   A palindromic integer reads the same forward and backward.

   Examples:
   >>> (sum-prefix-palindromes 3)
   [3 6]
   Explanation: palindromes are 1, 2, 3.

   >>> (sum-prefix-palindromes 12)
   [10 54]
   Explanation: palindromes are 1,2,3,4,5,6,7,8,9,11.

   Notes:
   - 1 <= n <= 10^6
   - Return [0 0] for nil or non-positive input."
  [n]
  (if (and n (pos? n))
    (let [pals (filter #(= (str %) (clojure.string/reverse (str %))) (range 1 (inc n)))]
      [(count pals) (reduce + 0 pals)])
    [0 0]))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 6] (sum-prefix-palindromes 3)))
  (is (= [10 54] (sum-prefix-palindromes 12)))
  (is (= [0 0] (sum-prefix-palindromes nil))))

(run-test test-variation)
