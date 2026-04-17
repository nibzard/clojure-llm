(require '[clojure.test :refer [deftest is run-test]])

(def candidate filter_integers)

(deftest test-humaneval

  (is (= (candidate []) []))
  (is (= (candidate [4 {} [] 23.2 9 "adasd"]) [4 9]))
  (is (= (candidate [3 "c" 3 3 "a" "b"]) [3 3 3]))
)

(run-test test-humaneval)