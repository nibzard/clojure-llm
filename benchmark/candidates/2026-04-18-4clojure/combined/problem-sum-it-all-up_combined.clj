(defn problem-sum-it-all-up [a]
  (reduce + a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-sum-it-all-up
  (is (= (= (problem-sum-it-all-up [1 2 3]) 6)))
  (is (= (= (problem-sum-it-all-up (list 0 -2 5 5)) 8)))
  (is (= (= (problem-sum-it-all-up #{4 2 1}) 7)))
  (is (= (= (problem-sum-it-all-up '(0 0 -1)) -1)))
  (is (= (= (problem-sum-it-all-up '(1 10 3)) 14))))

(run-tests)
