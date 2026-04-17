(require '[clojure.test :refer [deftest is run-test]])

(def candidate rounded_avg)

(deftest test-humaneval

  (is (= (candidate 1 5) "0b11"))
  (is (= (candidate 7 13) "0b1010"))
  (is (= (candidate 964 977) "0b1111001010"))
  (is (= (candidate 996 997) "0b1111100100"))
  (is (= (candidate 560 851) "0b1011000010"))
  (is (= (candidate 185 546) "0b101101110"))
  (is (= (candidate 362 496) "0b110101101"))
  (is (= (candidate 350 902) "0b1001110010"))
  (is (= (candidate 197 233) "0b11010111"))
  (is (= (candidate 7 5) -1))
  (is (= (candidate 5 1) -1))
  (is (= (candidate 5 5) "0b101"))
)

(run-test test-humaneval)