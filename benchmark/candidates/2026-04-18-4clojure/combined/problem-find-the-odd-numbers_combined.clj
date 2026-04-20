(defn problem-find-the-odd-numbers [a]
  (filter odd? a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-find-the-odd-numbers
  (is (= (= (problem-find-the-odd-numbers #{1 2 3 4 5}) '(1 3 5))))
  (is (= (= (problem-find-the-odd-numbers [4 2 1 6]) '(1))))
  (is (= (= (problem-find-the-odd-numbers [2 2 4 6]) '())))
  (is (= (= (problem-find-the-odd-numbers [1 1 1 3]) '(1 1 1 3)))))

(run-tests)
