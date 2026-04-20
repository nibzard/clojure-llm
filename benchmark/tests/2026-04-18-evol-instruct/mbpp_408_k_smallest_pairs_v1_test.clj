(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [{:a 1 :b 2 :sum 3}
          {:a 1 :b 4 :sum 5}
          {:a 1 :b 6 :sum 7}]
         (kth-smallest-sums [1 7 11] [2 4 6] 3)))
  (is (= []
         (kth-smallest-sums nil [1 2] 2)))
  (is (= []
         (kth-smallest-sums [1 2] [] 2)))
  (is (= []
         (kth-smallest-sums [1 2] [3 4] 0))))

(run-test test-variation)
