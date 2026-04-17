(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_char_position)

(deftest test-humaneval

  (is (= (candidate "xbcefg") 2))
  (is (= (candidate "ABcED") 3))
  (is (= (candidate "AbgdeF") 5))
)

(run-test test-humaneval)