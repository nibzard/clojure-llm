(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 4 5] (sort-by-bitcount [1 5 2 3 4])))
  (is (= [-6 -5 -4 -3 -2] (sort-by-bitcount [-2 -3 -4 -5 -6])))
  (is (= [] (sort-by-bitcount nil))))

(run-test test-variation)
