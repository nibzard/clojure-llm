(require '[clojure.test :refer [deftest is run-test]])

(def candidate rear_extract)

(deftest test-humaneval

  (is (= (candidate [[1 "Rash" 21] [2 "Varsha" 20] [3 "Kil" 19]]) [21 20 19]))
  (is (= (candidate [[1 "Sai" 36] [2 "Ayesha" 25] [3 "Salman" 45]]) [36 25 45]))
  (is (= (candidate [[1 "Sudeep" 14] [2 "Vandana" 36] [3 "Dawood" 56]]) [14 36 56]))
)

(run-test test-humaneval)