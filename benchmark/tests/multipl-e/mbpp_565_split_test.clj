(require '[clojure.test :refer [deftest is run-test]])

(def candidate split)

(deftest test-humaneval

  (is (= (candidate "python") ["p" "y" "t" "h" "o" "n"]))
  (is (= (candidate "Name") ["N" "a" "m" "e"]))
  (is (= (candidate "program") ["p" "r" "o" "g" "r" "a" "m"]))
)

(run-test test-humaneval)