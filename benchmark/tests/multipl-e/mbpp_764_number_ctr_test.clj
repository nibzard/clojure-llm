(require '[clojure.test :refer [deftest is run-test]])

(def candidate number_ctr)

(deftest test-humaneval

  (is (= (candidate "program2bedone") 1))
  (is (= (candidate "3wonders") 1))
  (is (= (candidate "123") 3))
  (is (= (candidate "3wond-1ers2") 3))
)

(run-test test-humaneval)