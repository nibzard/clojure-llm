(defn problem-a-half-truth
  [& bs]
  (and (some true? bs)
       (not-every? true? bs)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-a-half-truth
  (is (= (= false (problem-a-half-truth false false))))
  (is (= (= true (problem-a-half-truth true false))))
  (is (= (= false (problem-a-half-truth true))))
  (is (= (= true (problem-a-half-truth false true false))))
  (is (= (= false (problem-a-half-truth true true true))))
  (is (= (= true (problem-a-half-truth true true true false)))))

(run-tests)
