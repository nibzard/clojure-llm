(defn problem-replicate-a-sequence [a b]
  (mapcat #(repeat b %) a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-replicate-a-sequence
  (is (= (= (problem-replicate-a-sequence [1 2 3] 2) '(1 1 2 2 3 3))))
  (is (= (= (problem-replicate-a-sequence [:a :b] 4) '(:a :a :a :a :b :b :b :b))))
  (is (= (= (problem-replicate-a-sequence [4 5 6] 1) '(4 5 6))))
  (is (= (= (problem-replicate-a-sequence [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))))
  (is (= (= (problem-replicate-a-sequence [44 33] 2) [44 44 33 33]))))

(run-tests)
