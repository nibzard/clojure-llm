(require '[clojure.test :refer [deftest is run-test]])

(def candidate all_prefixes)

(deftest test-humaneval

  (is (= (candidate "") []))
  (is (= (candidate "asdfgh") ["a" "as" "asd" "asdf" "asdfg" "asdfgh"]))
  (is (= (candidate "WWW") ["W" "WW" "WWW"]))
)

(run-test test-humaneval)