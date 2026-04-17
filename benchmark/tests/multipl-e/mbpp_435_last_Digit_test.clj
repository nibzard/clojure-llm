(require '[clojure.test :refer [deftest is run-test]])

(def candidate last_Digit)

(deftest test-humaneval

  (is (= (candidate 123) 3))
  (is (= (candidate 25) 5))
  (is (= (candidate 30) 0))
)

(run-test test-humaneval)