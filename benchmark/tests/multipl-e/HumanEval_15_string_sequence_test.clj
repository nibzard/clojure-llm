(require '[clojure.test :refer [deftest is run-test]])

(def candidate string_sequence)

(deftest test-humaneval

  (is (= (candidate 0) "0"))
  (is (= (candidate 3) "0 1 2 3"))
  (is (= (candidate 10) "0 1 2 3 4 5 6 7 8 9 10"))
)

(run-test test-humaneval)