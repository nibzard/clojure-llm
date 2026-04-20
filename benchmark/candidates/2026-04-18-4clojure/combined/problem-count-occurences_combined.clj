(defn problem-count-occurences [a]
  (reduce (fn [m x] (assoc m x (inc (get m x 0)))) {} a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-count-occurences
  (is (= (= (problem-count-occurences [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})))
  (is (= (= (problem-count-occurences [:b :a :b :a :b]) {:a 2, :b 3})))
  (is (= (= (problem-count-occurences '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2}))))

(run-tests)
