(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (closest-num-in-seq 10 [3 8 9 12])))
  (is (= nil (closest-num-in-seq 5 [7 6 10])))
  (is (= 19 (closest-num-in-seq 20 '(1 19 18 5)))))

(run-test test-variation)
