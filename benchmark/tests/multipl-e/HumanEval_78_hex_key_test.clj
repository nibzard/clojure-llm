(require '[clojure.test :refer [deftest is run-test]])

(def candidate hex_key)

(deftest test-humaneval

  (is (= (candidate "AB") 1))
  (is (= (candidate "1077E") 2))
  (is (= (candidate "ABED1A33") 4))
  (is (= (candidate "2020") 2))
  (is (= (candidate "123456789ABCDEF0") 6))
  (is (= (candidate "112233445566778899AABBCCDDEEFF00") 12))
)

(run-test test-humaneval)