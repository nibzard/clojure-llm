(require '[clojure.test :refer [deftest is run-test]])

(def candidate geometric_sum)

(deftest test-humaneval

  (is (= (candidate 7) 1.9921875))
  (is (= (candidate 4) 1.9375))
  (is (= (candidate 8) 1.99609375))
)

(run-test test-humaneval)