(require '[clojure.test :refer [deftest is run-test]])

(def candidate prime_num)

(deftest test-humaneval

  (is (= (candidate 13) true))
  (is (= (candidate 7) true))
  (is (= (candidate -1010) false))
)

(run-test test-humaneval)