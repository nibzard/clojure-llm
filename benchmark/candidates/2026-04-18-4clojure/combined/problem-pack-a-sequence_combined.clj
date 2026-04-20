(defn problem-pack-a-sequence [a]
  (reduce
    (fn [acc x]
      (if (and (seq acc) (= x (last (last acc))))
        (concat (butlast acc) [(concat (last acc) [x])])
        (concat acc [(list x)])))
    '()
    a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-pack-a-sequence
  (is (= (= (problem-pack-a-sequence [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))))
  (is (= (= (problem-pack-a-sequence [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))))
  (is (= (= (problem-pack-a-sequence [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4]))))))

(run-tests)
