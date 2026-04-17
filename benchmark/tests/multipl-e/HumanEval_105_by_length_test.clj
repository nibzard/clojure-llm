(require '[clojure.test :refer [deftest is run-test]])

(def candidate by_length)

(deftest test-humaneval

  (is (= (candidate [2 1 1 4 5 8 2 3]) ["Eight" "Five" "Four" "Three" "Two" "Two" "One" "One"]))
  (is (= (candidate []) []))
  (is (= (candidate [1 -1 55]) ["One"]))
  (is (= (candidate [1 -1 3 2]) ["Three" "Two" "One"]))
  (is (= (candidate [9 4 8]) ["Nine" "Eight" "Four"]))
)

(run-test test-humaneval)