(require '[clojure.test :refer [deftest is run-test]])

(def candidate kth_element)

(deftest test-humaneval

  (is (= (candidate [12 3 5 7 19] 2) 3))
  (is (= (candidate [17 24 8 23] 3) 8))
  (is (= (candidate [16 21 25 36 4] 4) 36))
)

(run-test test-humaneval)