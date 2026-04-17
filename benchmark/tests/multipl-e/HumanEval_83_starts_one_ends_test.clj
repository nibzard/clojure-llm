(require '[clojure.test :refer [deftest is run-test]])

(def candidate starts_one_ends)

(deftest test-humaneval

  (is (= (candidate 1) 1))
  (is (= (candidate 2) 18))
  (is (= (candidate 3) 180))
  (is (= (candidate 4) 1800))
  (is (= (candidate 5) 18000))
)

(run-test test-humaneval)