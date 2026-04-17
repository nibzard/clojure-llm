(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_star_num)

(deftest test-humaneval

  (is (= (candidate 3) 37))
  (is (= (candidate 4) 73))
  (is (= (candidate 5) 121))
)

(run-test test-humaneval)