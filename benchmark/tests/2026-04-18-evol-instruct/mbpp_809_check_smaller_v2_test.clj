(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (check_smaller_lazy [3 5 8] [2 4 7])))
  (is (= false (check_smaller_lazy [3 5 8] [2 6 7])))
  (is (= false (check_smaller_lazy (repeat 10) (range)))))

(run-test test-variation)
