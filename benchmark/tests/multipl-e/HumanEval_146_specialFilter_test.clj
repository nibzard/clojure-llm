(require '[clojure.test :refer [deftest is run-test]])

(def candidate specialFilter)

(deftest test-humaneval

  (is (= (candidate [5 -2 1 -5]) 0))
  (is (= (candidate [15 -73 14 -15]) 1))
  (is (= (candidate [33 -2 -3 45 21 109]) 2))
  (is (= (candidate [43 -12 93 125 121 109]) 4))
  (is (= (candidate [71 -2 -33 75 21 19]) 3))
  (is (= (candidate [1]) 0))
  (is (= (candidate []) 0))
)

(run-test test-humaneval)