(require '[clojure.test :refer [deftest is run-test]])

(def candidate tuple_to_dict)

(deftest test-humaneval

  (is (= (candidate [1 5 7 10 13 5]) {1 5 7 10 13 5}))
  (is (= (candidate [1 2 3 4 5 6]) {1 2 3 4 5 6}))
  (is (= (candidate [7 8 9 10 11 12]) {7 8 9 10 11 12}))
)

(run-test test-humaneval)