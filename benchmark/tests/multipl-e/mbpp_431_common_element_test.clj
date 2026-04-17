(require '[clojure.test :refer [deftest is run-test]])

(def candidate common_element)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4 5] [5 6 7 8 9]) true))
  (is (= (candidate [1 2 3 4 5] [6 7 8 9]) nil))
  (is (= (candidate ["a" "b" "c"] ["d" "b" "e"]) true))
)

(run-test test-humaneval)