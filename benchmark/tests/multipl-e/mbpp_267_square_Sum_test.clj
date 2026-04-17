(require '[clojure.test :refer [deftest is run-test]])

(def candidate square_Sum)

(deftest test-humaneval

  (is (= (candidate 2) 10))
  (is (= (candidate 3) 35))
  (is (= (candidate 4) 84))
)

(run-test test-humaneval)