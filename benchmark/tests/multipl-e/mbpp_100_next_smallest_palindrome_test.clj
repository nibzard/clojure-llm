(require '[clojure.test :refer [deftest is run-test]])

(def candidate next_smallest_palindrome)

(deftest test-humaneval

  (is (= (candidate 99) 101))
  (is (= (candidate 1221) 1331))
  (is (= (candidate 120) 121))
)

(run-test test-humaneval)