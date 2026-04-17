(require '[clojure.test :refer [deftest is run-test]])

(def candidate any_int)

(deftest test-humaneval

  (is (= (candidate 2 3 1) true))
  (is (= (candidate 2.5 2 3) false))
  (is (= (candidate 1.5 5 3.5) false))
  (is (= (candidate 2 6 2) false))
  (is (= (candidate 4 2 2) true))
  (is (= (candidate 2.2 2.2 2.2) false))
  (is (= (candidate -4 6 2) true))
  (is (= (candidate 2 1 1) true))
  (is (= (candidate 3 4 7) true))
  (is (= (candidate 3.0 4 7) false))
)

(run-test test-humaneval)