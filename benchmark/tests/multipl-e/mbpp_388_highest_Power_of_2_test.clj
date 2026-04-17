(require '[clojure.test :refer [deftest is run-test]])

(def candidate highest_Power_of_2)

(deftest test-humaneval

  (is (= (candidate 10) 8))
  (is (= (candidate 19) 16))
  (is (= (candidate 32) 32))
)

(run-test test-humaneval)