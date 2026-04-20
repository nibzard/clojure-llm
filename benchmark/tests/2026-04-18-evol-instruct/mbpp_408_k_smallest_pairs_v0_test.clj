(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [{:a 1 :b 10 :sum 11}
          {:a 2 :b 10 :sum 12}
          {:a 1 :b 20 :sum 21}]
         (vec (k_smallest_sums [1 2] [10 20] 3)))))

(run-test test-variation)
