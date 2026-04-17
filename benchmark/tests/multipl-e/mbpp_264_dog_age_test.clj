(require '[clojure.test :refer [deftest is run-test]])

(def candidate dog_age)

(deftest test-humaneval

  (is (= (candidate 12) 61))
  (is (= (candidate 15) 73))
  (is (= (candidate 24) 109))
)

(run-test test-humaneval)