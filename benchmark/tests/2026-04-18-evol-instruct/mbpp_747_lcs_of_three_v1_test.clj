(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [\a \b \c \f]
         (common-subsequence "abcdef" "acbcf" "abcf")))
  (is (= [2 3]
         (common-subsequence [1 2 3 4] [2 4 3 1] [5 2 3 4])))
  (is (= []
         (common-subsequence nil "abc" "abc"))))

(run-test test-variation)
