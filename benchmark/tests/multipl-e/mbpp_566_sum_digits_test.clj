(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_digits)

(deftest test-humaneval

  (is (= (candidate 345) 12))
  (is (= (candidate 12) 3))
  (is (= (candidate 97) 16))
)

(run-test test-humaneval)