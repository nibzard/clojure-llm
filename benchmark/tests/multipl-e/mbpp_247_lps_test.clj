(require '[clojure.test :refer [deftest is run-test]])

(def candidate lps)

(deftest test-humaneval

  (is (= (candidate "TENS FOR TENS") 5))
  (is (= (candidate "CARDIO FOR CARDS") 7))
  (is (= (candidate "PART OF THE JOURNEY IS PART") 9))
)

(run-test test-humaneval)