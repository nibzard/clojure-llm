(require '[clojure.test :refer [deftest is run-test]])

(def candidate add_elements)

(deftest test-humaneval

  (is (= (candidate [1 -2 -3 41 57 76 87 88 99] 3) -4))
  (is (= (candidate [111 121 3 4000 5 6] 2) 0))
  (is (= (candidate [11 21 3 90 5 6 7 8 9] 4) 125))
  (is (= (candidate [111 21 3 4000 5 6 7 8 9] 4) 24))
  (is (= (candidate [1] 1) 1))
)

(run-test test-humaneval)