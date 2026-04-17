(require '[clojure.test :refer [deftest is run-test]])

(def candidate first_Digit)

(deftest test-humaneval

  (is (= (candidate 123) 1))
  (is (= (candidate 456) 4))
  (is (= (candidate 12) 1))
)

(run-test test-humaneval)