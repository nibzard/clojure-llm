(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (total_match [] [])))
  (is (= [[5] 6] (total_match [1 [2 3] 4] [[5] 6])))
  (is (= [[1 2] [3]] (total_match [[1 2] [3]] [4 5 6])))
  (is (= [[:x :y]] (total_match [[:a] :b :c] [[:x :y]])))
  (is (= nil (total_match nil nil))))

(run-test test-variation)
