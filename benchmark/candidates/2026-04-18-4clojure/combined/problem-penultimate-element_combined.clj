(defn problem-penultimate-element [a]
  (first (rest (reverse a))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-penultimate-element
  (is (= (= (problem-penultimate-element (list 1 2 3 4 5)) 4)))
  (is (= (= (problem-penultimate-element ["a" "b" "c"]) "b")))
  (is (= (= (problem-penultimate-element [[1 2] [3 4]]) [1 2]))))

(run-tests)
