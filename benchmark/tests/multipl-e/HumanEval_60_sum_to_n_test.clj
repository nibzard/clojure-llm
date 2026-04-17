(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum_to_n)

(deftest test-humaneval

  (is (= (candidate 1) 1))
  (is (= (candidate 6) 21))
  (is (= (candidate 11) 66))
  (is (= (candidate 30) 465))
  (is (= (candidate 100) 5050))
)

(run-test test-humaneval)