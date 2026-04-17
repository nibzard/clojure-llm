(require '[clojure.test :refer [deftest is run-test]])

(def candidate string_to_tuple)

(deftest test-humaneval

  (is (= (candidate "python 3.0") ["p" "y" "t" "h" "o" "n" "3" "." "0"]))
  (is (= (candidate "item1") ["i" "t" "e" "m" "1"]))
  (is (= (candidate "15.10") ["1" "5" "." "1" "0"]))
)

(run-test test-humaneval)