(defn problem-factorial-fun [a]
  (reduce * (range 1 (inc a))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-factorial-fun
  (is (= (= (problem-factorial-fun 1) 1)))
  (is (= (= (problem-factorial-fun 3) 6)))
  (is (= (= (problem-factorial-fun 5) 120)))
  (is (= (= (problem-factorial-fun 8) 40320))))

(run-tests)
