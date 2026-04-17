(require '[clojure.test :refer [deftest is run-test]])

(def candidate centered_hexagonal_number)

(deftest test-humaneval

  (is (= (candidate 10) 271))
  (is (= (candidate 2) 7))
  (is (= (candidate 9) 217))
)

(run-test test-humaneval)