(require '[clojure.test :refer [deftest is run-test]])

(def candidate tup_string)

(deftest test-humaneval

  (is (= (candidate ["e" "x" "e" "r" "c" "i" "s" "e" "s"]) "exercises"))
  (is (= (candidate ["p" "y" "t" "h" "o" "n"]) "python"))
  (is (= (candidate ["p" "r" "o" "g" "r" "a" "m"]) "program"))
)

(run-test test-humaneval)