(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(3 5 6 7 9 10 12 15) (polite-indexes 15)))
  (is (= '() (polite-indexes 1)))
  (is (= '(3) (polite-indexes 3))))

(run-test test-variation)
