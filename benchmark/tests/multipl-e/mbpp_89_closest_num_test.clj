(require '[clojure.test :refer [deftest is run-test]])

(def candidate closest_num)

(deftest test-humaneval

  (is (= (candidate 11) 10))
  (is (= (candidate 7) 6))
  (is (= (candidate 12) 11))
)

(run-test test-humaneval)