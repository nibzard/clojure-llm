(require '[clojure.test :refer [deftest is run-test]])

(def candidate left_rotate)

(deftest test-humaneval

  (is (= (candidate 16 2) 64))
  (is (= (candidate 10 2) 40))
  (is (= (candidate 99 3) 792))
  (is (= (candidate 99 3) 792))
  (is (= (candidate 1 3) 8))
  (is (= (candidate 5 3) 40))
  (is (= (candidate 29 3) 232))
)

(run-test test-humaneval)