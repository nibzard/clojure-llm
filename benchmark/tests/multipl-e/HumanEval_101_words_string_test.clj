(require '[clojure.test :refer [deftest is run-test]])

(def candidate words_string)

(deftest test-humaneval

  (is (= (candidate "Hi, my name is John") ["Hi" "my" "name" "is" "John"]))
  (is (= (candidate "One, two, three, four, five, six") ["One" "two" "three" "four" "five" "six"]))
  (is (= (candidate "Hi, my name") ["Hi" "my" "name"]))
  (is (= (candidate "One,, two, three, four, five, six,") ["One" "two" "three" "four" "five" "six"]))
  (is (= (candidate "") []))
  (is (= (candidate "ahmed     , gamal") ["ahmed" "gamal"]))
)

(run-test test-humaneval)