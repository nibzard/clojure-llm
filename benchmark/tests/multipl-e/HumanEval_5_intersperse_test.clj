(require '[clojure.test :refer [deftest is run-test]])

(def candidate intersperse)

(deftest test-humaneval

  (is (= (candidate [] 7) []))
  (is (= (candidate [5 6 3 2] 8) [5 8 6 8 3 8 2]))
  (is (= (candidate [2 2 2] 2) [2 2 2 2 2]))
)

(run-test test-humaneval)