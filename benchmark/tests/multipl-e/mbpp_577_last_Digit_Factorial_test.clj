(require '[clojure.test :refer [deftest is run-test]])

(def candidate last_Digit_Factorial)

(deftest test-humaneval

  (is (= (candidate 4) 4))
  (is (= (candidate 21) 0))
  (is (= (candidate 30) 0))
)

(run-test test-humaneval)