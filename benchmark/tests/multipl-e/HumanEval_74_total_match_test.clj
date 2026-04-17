(require '[clojure.test :refer [deftest is run-test]])

(def candidate total_match)

(deftest test-humaneval

  (is (= (candidate [] []) []))
  (is (= (candidate ["hi" "admin"] ["hi" "hi"]) ["hi" "hi"]))
  (is (= (candidate ["hi" "admin"] ["hi" "hi" "admin" "project"]) ["hi" "admin"]))
  (is (= (candidate ["4"] ["1" "2" "3" "4" "5"]) ["4"]))
  (is (= (candidate ["hi" "admin"] ["hI" "Hi"]) ["hI" "Hi"]))
  (is (= (candidate ["hi" "admin"] ["hI" "hi" "hi"]) ["hI" "hi" "hi"]))
  (is (= (candidate ["hi" "admin"] ["hI" "hi" "hii"]) ["hi" "admin"]))
  (is (= (candidate [] ["this"]) []))
  (is (= (candidate ["this"] []) []))
)

(run-test test-humaneval)