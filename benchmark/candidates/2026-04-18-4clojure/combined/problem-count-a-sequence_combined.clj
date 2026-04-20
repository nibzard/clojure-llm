(defn problem-count-a-sequence [a]
  (reduce (fn [n _] (inc n)) 0 a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-count-a-sequence
  (is (= (= (problem-count-a-sequence '(1 2 3 3 1)) 5)))
  (is (= (= (problem-count-a-sequence "Hello World") 11)))
  (is (= (= (problem-count-a-sequence [[1 2] [3 4] [5 6]]) 3)))
  (is (= (= (problem-count-a-sequence '(13)) 1)))
  (is (= (= (problem-count-a-sequence '(:a :b :c)) 3))))

(run-tests)
