(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_Volume)

(deftest test-humaneval

  (is (= (candidate 10 8 6) 240))
  (is (= (candidate 3 2 2) 6))
  (is (= (candidate 1 2 1) 1))
)

(run-test test-humaneval)