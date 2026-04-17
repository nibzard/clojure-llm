(require '[clojure.test :refer [deftest is run-test]])

(def candidate sequential_search)

(deftest test-humaneval

  (is (= (candidate [11 23 58 31 56 77 43 12 65 19] 31) [true 3]))
  (is (= (candidate [12 32 45 62 35 47 44 61] 61) [true 7]))
  (is (= (candidate [9 10 17 19 22 39 48 56] 48) [true 6]))
)

(run-test test-humaneval)