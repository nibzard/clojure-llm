(defn problem-split-a-sequence [a b]
  [(take a b) (drop a b)])

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-split-a-sequence
  (is (= (= (problem-split-a-sequence 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])))
  (is (= (= (problem-split-a-sequence 1 [:a :b :c :d]) [[:a] [:b :c :d]])))
  (is (= (= (problem-split-a-sequence 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]]))))

(run-tests)
