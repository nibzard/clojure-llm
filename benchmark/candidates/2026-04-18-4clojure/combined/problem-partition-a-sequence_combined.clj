(defn problem-partition-a-sequence [a b]
  (lazy-seq
    (let [chunk (take a b)]
      (when (= a (count chunk))
        (cons (apply list chunk)
              (problem-partition-a-sequence a (drop a b)))))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-partition-a-sequence
  (is (= (= (problem-partition-a-sequence 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))))
  (is (= (= (problem-partition-a-sequence 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))))
  (is (= (= (problem-partition-a-sequence 3 (range 8)) '((0 1 2) (3 4 5))))))

(run-tests)
