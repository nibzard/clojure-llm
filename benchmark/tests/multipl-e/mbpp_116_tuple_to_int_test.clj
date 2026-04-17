(require '[clojure.test :refer [deftest is run-test]])

(def candidate tuple_to_int)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) 123))
  (is (= (candidate [4 5 6]) 456))
  (is (= (candidate [5 6 7]) 567))
)

(run-test test-humaneval)