(require '[clojure.test :refer [deftest is run-test]])

(def candidate compare_one)

(deftest test-humaneval

  (is (= (candidate 1 2) 2))
  (is (= (candidate 1 2.5) 2.5))
  (is (= (candidate 2 3) 3))
  (is (= (candidate 5 6) 6))
  (is (= (candidate 1 "2,3") "2,3"))
  (is (= (candidate "5,1" "6") "6"))
  (is (= (candidate "1" "2") "2"))
  (is (= (candidate "1" 1) nil))
)

(run-test test-humaneval)