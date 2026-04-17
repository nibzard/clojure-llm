(require '[clojure.test :refer [deftest is run-test]])

(def candidate odd_Equivalent)

(deftest test-humaneval

  (is (= (candidate "011001" 6) 3))
  (is (= (candidate "11011" 5) 4))
  (is (= (candidate "1010" 4) 2))
)

(run-test test-humaneval)