(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '() (lucas-seq 0)))
  (is (= '(2) (lucas-seq 1)))
  (is (= '(2 1 3 4 7) (lucas-seq 5))))

(run-test test-variation)
