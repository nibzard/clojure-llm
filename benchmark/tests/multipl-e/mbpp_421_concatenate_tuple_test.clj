(require '[clojure.test :refer [deftest is run-test]])

(def candidate concatenate_tuple)

(deftest test-humaneval

  (is (= (candidate ["ID" "is" 4 "UTS"]) "ID-is-4-UTS"))
  (is (= (candidate ["QWE" "is" 4 "RTY"]) "QWE-is-4-RTY"))
  (is (= (candidate ["ZEN" "is" 4 "OP"]) "ZEN-is-4-OP"))
)

(run-test test-humaneval)