(require '[clojure.test :refer [deftest is run-test]])

(def candidate string_xor)

(deftest test-humaneval

  (is (= (candidate "111000" "101010") "010010"))
  (is (= (candidate "1" "1") "0"))
  (is (= (candidate "0101" "0000") "0101"))
)

(run-test test-humaneval)