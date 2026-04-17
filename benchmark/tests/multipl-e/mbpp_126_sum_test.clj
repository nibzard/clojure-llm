(require '[clojure.test :refer [deftest is run-test]])

(def candidate sum)

(deftest test-humaneval

  (is (= (candidate 10 15) 6))
  (is (= (candidate 100 150) 93))
  (is (= (candidate 4 6) 3))
)

(run-test test-humaneval)