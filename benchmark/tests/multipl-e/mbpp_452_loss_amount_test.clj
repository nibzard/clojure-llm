(require '[clojure.test :refer [deftest is run-test]])

(def candidate loss_amount)

(deftest test-humaneval

  (is (= (candidate 1500 1200) 0))
  (is (= (candidate 100 200) 100))
  (is (= (candidate 2000 5000) 3000))
)

(run-test test-humaneval)