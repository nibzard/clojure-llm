(require '[clojure.test :refer [deftest is run-test]])

(def candidate vowels_count)

(deftest test-humaneval

  (is (= (candidate "abcde") 2))
  (is (= (candidate "Alone") 3))
  (is (= (candidate "key") 2))
  (is (= (candidate "bye") 1))
  (is (= (candidate "keY") 2))
  (is (= (candidate "bYe") 1))
  (is (= (candidate "ACEDY") 3))
)

(run-test test-humaneval)