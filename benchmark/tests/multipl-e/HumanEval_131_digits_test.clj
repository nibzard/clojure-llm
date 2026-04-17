(require '[clojure.test :refer [deftest is run-test]])

(def candidate digits)

(deftest test-humaneval

  (is (= (candidate 5) 5))
  (is (= (candidate 54) 5))
  (is (= (candidate 120) 1))
  (is (= (candidate 5014) 5))
  (is (= (candidate 98765) 315))
  (is (= (candidate 5576543) 2625))
  (is (= (candidate 2468) 0))
)

(run-test test-humaneval)