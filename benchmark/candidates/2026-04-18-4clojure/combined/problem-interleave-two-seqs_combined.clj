(defn problem-interleave-two-seqs [a b]
  (mapcat list a b))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-interleave-two-seqs
  (is (= (= (problem-interleave-two-seqs [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))))
  (is (= (= (problem-interleave-two-seqs [1 2] [3 4 5 6]) '(1 3 2 4))))
  (is (= (= (problem-interleave-two-seqs [1 2 3 4] [5]) [1 5])))
  (is (= (= (problem-interleave-two-seqs [30 20] [25 15]) [30 25 20 15]))))

(run-tests)
