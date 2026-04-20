(defn problem-fibonacci-sequence [a]
  (take a (map first (iterate (fn [[x y]] [y (+ x y)]) [1 1]))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-fibonacci-sequence
  (is (= (= (problem-fibonacci-sequence 3) '(1 1 2))))
  (is (= (= (problem-fibonacci-sequence 6) '(1 1 2 3 5 8))))
  (is (= (= (problem-fibonacci-sequence 8) '(1 1 2 3 5 8 13 21)))))

(run-tests)
