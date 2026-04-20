(defn problem-perfect-numbers [a]
  (= a
     (reduce +
             (filter #(zero? (mod a %))
                     (range 1 a)))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-perfect-numbers
  (is (= (= (problem-perfect-numbers 6) true)))
  (is (= (= (problem-perfect-numbers 7) false)))
  (is (= (= (problem-perfect-numbers 496) true)))
  (is (= (= (problem-perfect-numbers 500) false)))
  (is (= (= (problem-perfect-numbers 8128) true))))

(run-tests)
