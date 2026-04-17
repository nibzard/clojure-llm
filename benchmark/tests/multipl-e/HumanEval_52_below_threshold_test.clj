(require '[clojure.test :refer [deftest is run-test]])

(def candidate below_threshold)

(deftest test-humaneval

  (is (= (candidate [1 2 4 10] 100) true))
  (is (= (candidate [1 20 4 10] 5) false))
  (is (= (candidate [1 20 4 10] 21) true))
  (is (= (candidate [1 20 4 10] 22) true))
  (is (= (candidate [1 8 4 10] 11) true))
  (is (= (candidate [1 8 4 10] 10) false))
)

(run-test test-humaneval)