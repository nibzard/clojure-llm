(require '[clojure.test :refer [deftest is run-test]])

(def candidate extract_freq)

(deftest test-humaneval

  (is (= (candidate [[3 4] [1 2] [4 3] [5 6]]) 3))
  (is (= (candidate [[4 15] [2 3] [5 4] [6 7]]) 4))
  (is (= (candidate [[5 16] [2 3] [6 5] [6 9]]) 4))
)

(run-test test-humaneval)