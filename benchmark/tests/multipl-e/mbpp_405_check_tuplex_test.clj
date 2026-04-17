(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_tuplex)

(deftest test-humaneval

  (is (= (candidate ["w" 3 "r" "e" "s" "o" "u" "r" "c" "e"] "r") true))
  (is (= (candidate ["w" 3 "r" "e" "s" "o" "u" "r" "c" "e"] "5") false))
  (is (= (candidate ["w" 3 "r" "e" "s" "o" "u" "r" "c" "e"] 3) true))
)

(run-test test-humaneval)