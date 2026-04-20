(defn problem-reverse-a-sequence [a]
  (reduce conj '() a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-reverse-a-sequence
  (is (= (= (problem-reverse-a-sequence [1 2 3 4 5]) [5 4 3 2 1])))
  (is (= (= (problem-reverse-a-sequence (sorted-set 5 7 2 7)) '(7 5 2))))
  (is (= (= (problem-reverse-a-sequence [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]]))))

(run-tests)
