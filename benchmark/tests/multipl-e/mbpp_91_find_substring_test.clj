(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_substring)

(deftest test-humaneval

  (is (= (candidate ["red" "black" "white" "green" "orange"] "ack") true))
  (is (= (candidate ["red" "black" "white" "green" "orange"] "abc") false))
  (is (= (candidate ["red" "black" "white" "green" "orange"] "ange") true))
)

(run-test test-humaneval)