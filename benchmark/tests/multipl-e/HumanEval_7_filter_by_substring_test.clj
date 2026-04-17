(require '[clojure.test :refer [deftest is run-test]])

(def candidate filter_by_substring)

(deftest test-humaneval

  (is (= (candidate [] "john") []))
  (is (= (candidate ["xxx" "asd" "xxy" "john doe" "xxxAAA" "xxx"] "xxx") ["xxx" "xxxAAA" "xxx"]))
  (is (= (candidate ["xxx" "asd" "aaaxxy" "john doe" "xxxAAA" "xxx"] "xx") ["xxx" "aaaxxy" "xxxAAA" "xxx"]))
  (is (= (candidate ["grunt" "trumpet" "prune" "gruesome"] "run") ["grunt" "prune"]))
)

(run-test test-humaneval)