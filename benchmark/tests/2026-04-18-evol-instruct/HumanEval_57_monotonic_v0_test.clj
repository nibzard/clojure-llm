(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 2 5] (monotonic-prefix [1 2 2 5 3 4])))
  (is (= '(9 7 7 2) (monotonic-prefix '(9 7 7 2 3))))
  (is (= [5] (monotonic-prefix [5])))
  (is (= [] (monotonic-prefix []))))

(run-test test-variation)
