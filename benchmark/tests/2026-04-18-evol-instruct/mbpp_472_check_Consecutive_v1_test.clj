(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 2 3] [5 6] [8]] (consecutive-ranges [1 2 3 5 6 8])))
  (is (= [] (doall (consecutive-ranges []))))
  (is (= [[0 1 2] [3 4 5]]
         (take 2 (consecutive-ranges (partition 3 (range 6)))))))

(run-test test-variation)
