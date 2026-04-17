(require '[clojure.test :refer [deftest is run-test]])

(def candidate f)

(deftest test-humaneval

  (is (= (candidate 5) [1 2 6 24 15]))
  (is (= (candidate 7) [1 2 6 24 15 720 28]))
  (is (= (candidate 1) [1]))
  (is (= (candidate 3) [1 2 6]))
)

(run-test test-humaneval)