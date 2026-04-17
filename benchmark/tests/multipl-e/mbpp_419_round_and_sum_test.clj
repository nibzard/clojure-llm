(require '[clojure.test :refer [deftest is run-test]])

(def candidate round_and_sum)

(deftest test-humaneval

  (is (= (candidate [22.4 4.0 -16.22 -9.1 11.0 -12.22 14.2 -5.2 17.5]) 243))
  (is (= (candidate [5 2 9 24.3 29]) 345))
  (is (= (candidate [25.0 56.7 89.2]) 513))
)

(run-test test-humaneval)