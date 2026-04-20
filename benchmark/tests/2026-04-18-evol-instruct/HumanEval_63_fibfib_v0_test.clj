(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(6 9 12) (window-sums 3 [1 2 3 4 5])))
  (is (= '(30 50) (window-sums 2 '(10 20 30))))
  (is (= '() (window-sums 4 [1 2])))
  (is (= '() (window-sums 0 [1 2 3]))))

(run-test test-variation)
