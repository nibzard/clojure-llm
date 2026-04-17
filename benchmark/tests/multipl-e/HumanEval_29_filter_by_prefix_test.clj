(require '[clojure.test :refer [deftest is run-test]])

(def candidate filter_by_prefix)

(deftest test-humaneval

  (is (= (candidate [] "john") []))
  (is (= (candidate ["xxx" "asd" "xxy" "john doe" "xxxAAA" "xxx"] "xxx") ["xxx" "xxxAAA" "xxx"]))
)

(run-test test-humaneval)