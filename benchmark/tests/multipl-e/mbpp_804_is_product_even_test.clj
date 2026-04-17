(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_product_even)

(deftest test-humaneval

  (is (= (candidate [1 2 3]) true))
  (is (= (candidate [1 2 1 4]) true))
  (is (= (candidate [1 1]) false))
)

(run-test test-humaneval)