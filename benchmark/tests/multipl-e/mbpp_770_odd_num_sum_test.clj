(require '[clojure.test :refer [deftest is run-test]])

(def candidate odd_num_sum)

(deftest test-humaneval

  (is (= (candidate 2) 82))
  (is (= (candidate 3) 707))
  (is (= (candidate 4) 3108))
)

(run-test test-humaneval)