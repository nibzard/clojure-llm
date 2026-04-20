(defn problem-rotate-sequence [a b]
  (let [n (count b)
        r (mod a n)]
    (concat (drop r b) (take r b))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-rotate-sequence
  (is (= (= (problem-rotate-sequence 2 [1 2 3 4 5]) '(3 4 5 1 2))))
  (is (= (= (problem-rotate-sequence -2 [1 2 3 4 5]) '(4 5 1 2 3))))
  (is (= (= (problem-rotate-sequence 6 [1 2 3 4 5]) '(2 3 4 5 1))))
  (is (= (= (problem-rotate-sequence 1 '(:a :b :c)) '(:b :c :a))))
  (is (= (= (problem-rotate-sequence -4 '(:a :b :c)) '(:c :a :b)))))

(run-tests)
