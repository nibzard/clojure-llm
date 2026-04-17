(require '[clojure.test :refer [deftest is run-test]])

(def candidate car_race_collision)

(deftest test-humaneval

  (is (= (candidate 2) 4))
  (is (= (candidate 3) 9))
  (is (= (candidate 4) 16))
  (is (= (candidate 8) 64))
  (is (= (candidate 10) 100))
)

(run-test test-humaneval)