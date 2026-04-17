(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_product)

(deftest test-humaneval

  (is (= (candidate [3 100 4 5 150 6]) 3000))
  (is (= (candidate [4 42 55 68 80]) 50265600))
  (is (= (candidate [10 22 9 33 21 50 41 60]) 2460))
)

(run-test test-humaneval)