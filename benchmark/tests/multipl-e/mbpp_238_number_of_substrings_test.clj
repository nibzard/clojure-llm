(require '[clojure.test :refer [deftest is run-test]])

(def candidate number_of_substrings)

(deftest test-humaneval

  (is (= (candidate "abc") 6))
  (is (= (candidate "abcd") 10))
  (is (= (candidate "abcde") 15))
)

(run-test test-humaneval)