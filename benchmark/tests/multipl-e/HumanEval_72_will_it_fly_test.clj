(require '[clojure.test :refer [deftest is run-test]])

(def candidate will_it_fly)

(deftest test-humaneval

  (is (= (candidate [3 2 3] 9) true))
  (is (= (candidate [1 2] 5) false))
  (is (= (candidate [3] 5) true))
  (is (= (candidate [3 2 3] 1) false))
  (is (= (candidate [1 2 3] 6) false))
  (is (= (candidate [5] 5) true))
)

(run-test test-humaneval)