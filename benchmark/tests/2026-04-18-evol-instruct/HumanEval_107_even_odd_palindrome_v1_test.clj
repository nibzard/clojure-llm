(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 6] (sum-prefix-palindromes 3)))
  (is (= [10 54] (sum-prefix-palindromes 12)))
  (is (= [0 0] (sum-prefix-palindromes nil))))

(run-test test-variation)
