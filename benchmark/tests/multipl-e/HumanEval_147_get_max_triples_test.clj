(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_max_triples)

(deftest test-humaneval

  (is (= (candidate 5) 1))
  (is (= (candidate 6) 4))
  (is (= (candidate 10) 36))
  (is (= (candidate 100) 53361))
)

(run-test test-humaneval)