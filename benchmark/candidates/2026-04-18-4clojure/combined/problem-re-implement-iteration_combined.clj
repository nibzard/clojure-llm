(defn problem-re-implement-iteration [a b]
  (lazy-seq
    (cons b (problem-re-implement-iteration a (a b)))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-re-implement-iteration
  (is (= (= (take 5 (problem-re-implement-iteration #(* 2 %) 1)) [1 2 4 8 16])))
  (is (= (= (take 100 (problem-re-implement-iteration inc 0)) (take 100 (range)))))
  (is (= (= (take 9 (problem-re-implement-iteration #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3]))))))

(run-tests)
