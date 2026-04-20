(defn problem-duplicate-a-sequence [a]
  (into (empty a) (mapcat #(list % %) a)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-duplicate-a-sequence
  (is (= (= (problem-duplicate-a-sequence [1 2 3]) '(1 1 2 2 3 3))))
  (is (= (= (problem-duplicate-a-sequence [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))))
  (is (= (= (problem-duplicate-a-sequence [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))))
  (is (= (= (problem-duplicate-a-sequence [44 33]) [44 44 33 33]))))

(run-tests)
