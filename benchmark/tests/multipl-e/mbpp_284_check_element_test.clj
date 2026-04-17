(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_element)

(deftest test-humaneval

  (is (= (candidate ["green" "orange" "black" "white"] "blue") false))
  (is (= (candidate [1 2 3 4] 7) false))
  (is (= (candidate ["green" "green" "green" "green"] "green") true))
)

(run-test test-humaneval)