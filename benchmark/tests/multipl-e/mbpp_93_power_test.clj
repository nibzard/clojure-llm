(require '[clojure.test :refer [deftest is run-test]])

(def candidate power)

(deftest test-humaneval

  (is (= (candidate 3 4) 81))
  (is (= (candidate 2 3) 8))
  (is (= (candidate 5 5) 3125))
)

(run-test test-humaneval)