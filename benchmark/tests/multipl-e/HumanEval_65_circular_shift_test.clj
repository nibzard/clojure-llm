(require '[clojure.test :refer [deftest is run-test]])

(def candidate circular_shift)

(deftest test-humaneval

  (is (= (candidate 100 2) "001"))
  (is (= (candidate 12 2) "12"))
  (is (= (candidate 97 8) "79"))
  (is (= (candidate 12 1) "21"))
  (is (= (candidate 11 101) "11"))
)

(run-test test-humaneval)