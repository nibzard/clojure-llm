(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '([1 4] [2 3]) (find_combinations_lazy 5 [1 2 3 4]))))

(run-test test-variation)
