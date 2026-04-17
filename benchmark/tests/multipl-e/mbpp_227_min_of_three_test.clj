(require '[clojure.test :refer [deftest is run-test]])

(def candidate min_of_three)

(deftest test-humaneval

  (is (= (candidate 10 20 0) 0))
  (is (= (candidate 19 15 18) 15))
  (is (= (candidate -10 -20 -30) -30))
)

(run-test test-humaneval)