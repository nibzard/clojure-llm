(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_num_decagonal)

(deftest test-humaneval

  (is (= (candidate 3) 27))
  (is (= (candidate 7) 175))
  (is (= (candidate 10) 370))
)

(run-test test-humaneval)