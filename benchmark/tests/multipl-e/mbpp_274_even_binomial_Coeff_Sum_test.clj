(require '[clojure.test :refer [deftest is run-test]])

(def candidate even_binomial_Coeff_Sum)

(deftest test-humaneval

  (is (= (candidate 4) 8))
  (is (= (candidate 6) 32))
  (is (= (candidate 2) 2))
)

(run-test test-humaneval)