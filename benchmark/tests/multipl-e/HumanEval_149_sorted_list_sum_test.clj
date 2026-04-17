(require '[clojure.test :refer [deftest is run-test]])

(def candidate sorted_list_sum)

(deftest test-humaneval

  (is (= (candidate ["aa" "a" "aaa"]) ["aa"]))
  (is (= (candidate ["school" "AI" "asdf" "b"]) ["AI" "asdf" "school"]))
  (is (= (candidate ["d" "b" "c" "a"]) []))
  (is (= (candidate ["d" "dcba" "abcd" "a"]) ["abcd" "dcba"]))
  (is (= (candidate ["AI" "ai" "au"]) ["AI" "ai" "au"]))
  (is (= (candidate ["a" "b" "b" "c" "c" "a"]) []))
  (is (= (candidate ["aaaa" "bbbb" "dd" "cc"]) ["cc" "dd" "aaaa" "bbbb"]))
)

(run-test test-humaneval)