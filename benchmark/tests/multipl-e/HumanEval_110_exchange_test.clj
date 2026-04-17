(require '[clojure.test :refer [deftest is run-test]])

(def candidate exchange)

(deftest test-humaneval

  (is (= (candidate [1 2 3 4] [1 2 3 4]) "YES"))
  (is (= (candidate [1 2 3 4] [1 5 3 4]) "NO"))
  (is (= (candidate [1 2 3 4] [2 1 4 3]) "YES"))
  (is (= (candidate [5 7 3] [2 6 4]) "YES"))
  (is (= (candidate [5 7 3] [2 6 3]) "NO"))
  (is (= (candidate [3 2 6 1 8 9] [3 5 5 1 1 1]) "NO"))
  (is (= (candidate [100 200] [200 200]) "YES"))
)

(run-test test-humaneval)