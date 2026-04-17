(require '[clojure.test :refer [deftest is run-test]])

(def candidate multiply)

(deftest test-humaneval

  (is (= (candidate 148 412) 16))
  (is (= (candidate 19 28) 72))
  (is (= (candidate 2020 1851) 0))
  (is (= (candidate 14 -15) 20))
  (is (= (candidate 76 67) 42))
  (is (= (candidate 17 27) 49))
  (is (= (candidate 0 1) 0))
  (is (= (candidate 0 0) 0))
)

(run-test test-humaneval)