(require '[clojure.test :refer [deftest is run-test]])

(def candidate large_product)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5 6] [3 6 8 9 10 6] 3) [60 54 50]))
  (is (= (candidate [1 2 3 4 5 6] [3 6 8 9 10 6] 4) [60 54 50 48]))
  (is (= (candidate [1 2 3 4 5 6] [3 6 8 9 10 6] 5) [60 54 50 48 45]))
)

(run-test test-humaneval)