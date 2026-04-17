(require '[clojure.test :refer [deftest is run-test]])

(def candidate even_odd_palindrome)

(deftest test-humaneval

  (is (= (candidate 123) [8 13]))
  (is (= (candidate 12) [4 6]))
  (is (= (candidate 3) [1 2]))
  (is (= (candidate 63) [6 8]))
  (is (= (candidate 25) [5 6]))
  (is (= (candidate 19) [4 6]))
  (is (= (candidate 9) [4 5]))
  (is (= (candidate 1) [0 1]))
)

(run-test test-humaneval)