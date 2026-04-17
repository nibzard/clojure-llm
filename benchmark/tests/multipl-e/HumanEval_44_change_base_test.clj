(require '[clojure.test :refer [deftest is run-test]])

(def candidate change_base)

(deftest test-humaneval

  (is (= (candidate 8 3) "22"))
  (is (= (candidate 9 3) "100"))
  (is (= (candidate 234 2) "11101010"))
  (is (= (candidate 16 2) "10000"))
  (is (= (candidate 8 2) "1000"))
  (is (= (candidate 7 2) "111"))
  (is (= (candidate 2 3) "2"))
  (is (= (candidate 3 4) "3"))
  (is (= (candidate 4 5) "4"))
  (is (= (candidate 5 6) "5"))
  (is (= (candidate 6 7) "6"))
  (is (= (candidate 7 8) "7"))
)

(run-test test-humaneval)