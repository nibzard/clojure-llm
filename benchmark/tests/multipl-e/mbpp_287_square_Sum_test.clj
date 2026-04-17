(require '[clojure.test :refer [deftest is run-test]])

(def candidate square_Sum)

(deftest test-humaneval

  (is (= (candidate 2) 20))
  (is (= (candidate 3) 56))
  (is (= (candidate 4) 120))
)

(run-test test-humaneval)