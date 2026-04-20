(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 4 -1 2] (max_sum-subseq [1 -2 3 4 -1 2])))
  (is (= [-1] (max_sum-subseq [-5 -1 -3])))
  (is (= nil (max_sum-subseq nil))))

(run-test test-variation)
