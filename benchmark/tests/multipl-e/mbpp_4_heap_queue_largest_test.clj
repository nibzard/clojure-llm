(require '[clojure.test :refer [deftest is run-test]])

(def candidate heap_queue_largest)

(deftest test-humaneval

  (is (= (candidate [25 35 22 85 14 65 75 22 58] 3) [85 75 65]))
  (is (= (candidate [25 35 22 85 14 65 75 22 58] 2) [85 75]))
  (is (= (candidate [25 35 22 85 14 65 75 22 58] 5) [85 75 65 58 35]))
)

(run-test test-humaneval)