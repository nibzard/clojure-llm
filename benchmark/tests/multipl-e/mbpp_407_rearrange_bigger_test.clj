(require '[clojure.test :refer [deftest is run-test]])

(def candidate rearrange_bigger)

(deftest test-humaneval

  (is (= (candidate 12) 21))
  (is (= (candidate 10) false))
  (is (= (candidate 102) 120))
)

(run-test test-humaneval)