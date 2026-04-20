(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (can-partition-even-sum [1 5 11 5])))
  (is (= false (can-partition-even-sum [1 2 3 5])))
  (is (= false (can-partition-even-sum [])))
  (is (= true (can-partition-even-sum (range 1 6)))))

(run-test test-variation)
