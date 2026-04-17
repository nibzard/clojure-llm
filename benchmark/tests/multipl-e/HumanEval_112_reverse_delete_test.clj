(require '[clojure.test :refer [deftest is run-test]])

(def candidate reverse_delete)

(deftest test-humaneval

  (is (= (candidate "abcde" "ae") ["bcd" false]))
  (is (= (candidate "abcdef" "b") ["acdef" false]))
  (is (= (candidate "abcdedcba" "ab") ["cdedc" true]))
  (is (= (candidate "dwik" "w") ["dik" false]))
  (is (= (candidate "a" "a") ["" true]))
  (is (= (candidate "abcdedcba" "") ["abcdedcba" true]))
  (is (= (candidate "abcdedcba" "v") ["abcdedcba" true]))
  (is (= (candidate "vabba" "v") ["abba" true]))
  (is (= (candidate "mamma" "mia") ["" true]))
)

(run-test test-humaneval)