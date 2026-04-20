(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (median-of-three 3 1 2)))
  (is (= 10 (median-of-three 10 10 5)))
  (is (= 3 (median-of-three -1 7 3))))

(run-test test-variation)
