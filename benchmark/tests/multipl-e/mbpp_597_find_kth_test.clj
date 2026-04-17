(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_kth)

(deftest test-humaneval

  (is (= (candidate [2 3 6 7 9] [1 4 8 10] 5) 6))
  (is (= (candidate [100 112 256 349 770] [72 86 113 119 265 445 892] 7) 256))
  (is (= (candidate [3 4 7 8 10] [2 5 9 11] 6) 8))
)

(run-test test-humaneval)