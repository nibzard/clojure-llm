(defn problem-implement-range [a b]
  (loop [i a
         acc '()]
    (if (>= i b)
      (reverse acc)
      (recur (inc i) (cons i acc)))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-implement-range
  (is (= (= (problem-implement-range 1 4) '(1 2 3))))
  (is (= (= (problem-implement-range -2 2) '(-2 -1 0 1))))
  (is (= (= (problem-implement-range 5 8) '(5 6 7)))))

(run-tests)
