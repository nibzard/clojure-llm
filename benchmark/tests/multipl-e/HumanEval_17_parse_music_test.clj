(require '[clojure.test :refer [deftest is run-test]])

(def candidate parse_music)

(deftest test-humaneval

  (is (= (candidate "") []))
  (is (= (candidate "o o o o") [4 4 4 4]))
  (is (= (candidate ".| .| .| .|") [1 1 1 1]))
  (is (= (candidate "o| o| .| .| o o o o") [2 2 1 1 4 4 4 4]))
  (is (= (candidate "o| .| o| .| o o| o o|") [2 1 2 1 4 2 4 2]))
)

(run-test test-humaneval)