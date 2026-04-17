(require '[clojure.test :refer [deftest is run-test]])

(def candidate min_Swaps)

(deftest test-humaneval

  (is (= (candidate "1101" "1110") 1))
  (is (= (candidate "111" "000") "Not Possible"))
  (is (= (candidate "111" "110") "Not Possible"))
)

(run-test test-humaneval)