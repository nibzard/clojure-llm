(defn problem-sequence-reductions
  ([f coll]
   (lazy-seq
     (when-let [s (seq coll)]
       (problem-sequence-reductions f (first s) (rest s)))))
  ([f init coll]
   (lazy-seq
     (cons init
           (when-let [s (seq coll)]
             (problem-sequence-reductions f (f init (first s)) (rest s)))))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-sequence-reductions
  (is (= (= (take 5 (problem-sequence-reductions + (range))) [0 1 3 6 10])))
  (is (= (= (problem-sequence-reductions conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])))
  (is (= (= (last (problem-sequence-reductions * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))))

(run-tests)
