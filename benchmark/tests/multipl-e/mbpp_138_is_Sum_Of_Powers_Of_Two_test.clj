(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_Sum_Of_Powers_Of_Two)

(deftest test-humaneval

  (is (= (candidate 10) true))
  (is (= (candidate 7) false))
  (is (= (candidate 14) true))
)

(run-test test-humaneval)