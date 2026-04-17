(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_perfect_square)

(deftest test-humaneval

  (is (= (candidate 10) false))
  (is (= (candidate 36) true))
  (is (= (candidate 14) false))
  (is (= (candidate 196) true))
  (is (= (candidate 125) false))
  (is (= (candidate 15625) true))
)

(run-test test-humaneval)