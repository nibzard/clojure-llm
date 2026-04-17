(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_length)

(deftest test-humaneval

  (is (= (candidate "11000010001") 6))
  (is (= (candidate "10111") 1))
  (is (= (candidate "11011101100101") 2))
)

(run-test test-humaneval)