(require '[clojure.test :refer [deftest is run-test]])

(def candidate even_Power_Sum)

(deftest test-humaneval

  (is (= (candidate 2) 1056))
  (is (= (candidate 3) 8832))
  (is (= (candidate 1) 32))
)

(run-test test-humaneval)