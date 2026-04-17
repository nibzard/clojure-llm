(require '[clojure.test :refer [deftest is run-test]])

(def candidate factorize)

(deftest test-humaneval

  (is (= (candidate 2) [2]))
  (is (= (candidate 4) [2 2]))
  (is (= (candidate 8) [2 2 2]))
  (is (= (candidate 57) [3 19]))
  (is (= (candidate 3249) [3 3 19 19]))
  (is (= (candidate 185193) [3 3 3 19 19 19]))
  (is (= (candidate 20577) [3 19 19 19]))
  (is (= (candidate 18) [2 3 3]))
)

(run-test test-humaneval)