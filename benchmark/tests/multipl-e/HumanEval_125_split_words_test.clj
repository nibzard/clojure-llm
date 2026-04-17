(require '[clojure.test :refer [deftest is run-test]])

(def candidate split_words)

(deftest test-humaneval

  (is (= (candidate "Hello world!") ["Hello" "world!"]))
  (is (= (candidate "Hello,world!") ["Hello" "world!"]))
  (is (= (candidate "Hello world,!") ["Hello" "world,!"]))
  (is (= (candidate "Hello,Hello,world !") ["Hello,Hello,world" "!"]))
  (is (= (candidate "abcdef") 3))
  (is (= (candidate "aaabb") 2))
  (is (= (candidate "aaaBb") 1))
  (is (= (candidate "") 0))
)

(run-test test-humaneval)