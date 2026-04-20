(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["little"] (select_words "Mary had a little lamb" 4)))
  (is (= ["world!"] (select_words "Hello, world!" 4)))
  (is (= [] (select_words nil 2)))
  (is (= ["a-b" "c_d"] (select_words "a-b c_d" 1)))
  (is (= [] (select_words "anything at all" -1))))

(run-test test-variation)
