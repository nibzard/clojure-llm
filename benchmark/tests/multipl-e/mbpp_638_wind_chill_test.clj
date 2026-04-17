(require '[clojure.test :refer [deftest is run-test]])

(def candidate wind_chill)

(deftest test-humaneval

  (is (= (candidate 120 35) 40))
  (is (= (candidate 40 20) 19))
  (is (= (candidate 10 8) 6))
)

(run-test test-humaneval)