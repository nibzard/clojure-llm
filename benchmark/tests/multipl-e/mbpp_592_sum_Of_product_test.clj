(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_Of_product)

(deftest test-humaneval

  (is (= (candidate 3) 15))
  (is (= (candidate 4) 56))
  (is (= (candidate 1) 1))
)

(run-test test-humaneval)