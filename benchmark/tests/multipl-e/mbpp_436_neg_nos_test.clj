(require '[clojure.test :refer [deftest is run-test]])

(def candidate neg_nos)

(deftest test-humaneval

  (is (= (candidate [-1 4 5 -6]) [-1 -6]))
  (is (= (candidate [-1 -2 3 4]) [-1 -2]))
  (is (= (candidate [-7 -6 8 9]) [-7 -6]))
)

(run-test test-humaneval)