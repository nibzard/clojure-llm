(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_max)

(deftest test-humaneval

  (is (= (candidate ["name" "of" "string"]) "string"))
  (is (= (candidate ["name" "enam" "game"]) "enam"))
  (is (= (candidate ["aaaaaaa" "bb" "cc"]) "aaaaaaa"))
  (is (= (candidate ["abc" "cba"]) "abc"))
  (is (= (candidate ["play" "this" "game" "of" "footbott"]) "footbott"))
  (is (= (candidate ["we" "are" "gonna" "rock"]) "gonna"))
  (is (= (candidate ["we" "are" "a" "mad" "nation"]) "nation"))
  (is (= (candidate ["this" "is" "a" "prrk"]) "this"))
  (is (= (candidate ["b"]) "b"))
  (is (= (candidate ["play" "play" "play"]) "play"))
)

(run-test test-humaneval)