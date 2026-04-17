(require '[clojure.test :refer [deftest is run-test]])

(def candidate select_words)

(deftest test-humaneval

  (is (= (candidate "Mary had a little lamb" 4) ["little"]))
  (is (= (candidate "Mary had a little lamb" 3) ["Mary" "lamb"]))
  (is (= (candidate "simple white space" 2) []))
  (is (= (candidate "Hello world" 4) ["world"]))
  (is (= (candidate "Uncle sam" 3) ["Uncle"]))
  (is (= (candidate "" 4) []))
  (is (= (candidate "a b c d e f" 1) ["b" "c" "d" "f"]))
)

(run-test test-humaneval)