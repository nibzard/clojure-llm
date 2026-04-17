(require '[clojure.test :refer [deftest is run-test]])

(def candidate tri)

(deftest test-humaneval

  (is (= (candidate 3) [1 3 2 8]))
  (is (= (candidate 4) [1 3 2 8 3]))
  (is (= (candidate 5) [1 3 2 8 3 15]))
  (is (= (candidate 6) [1 3 2 8 3 15 4]))
  (is (= (candidate 7) [1 3 2 8 3 15 4 24]))
  (is (= (candidate 8) [1 3 2 8 3 15 4 24 5]))
  (is (= (candidate 9) [1 3 2 8 3 15 4 24 5 35]))
  (is (= (candidate 20) [1 3 2 8 3 15 4 24 5 35 6 48 7 63 8 80 9 99 10 120 11]))
  (is (= (candidate 0) [1]))
  (is (= (candidate 1) [1 3]))
)

(run-test test-humaneval)