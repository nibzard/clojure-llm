(defn problem-maximum-value
  [& xs]
  (reduce (fn [m x] (if (> x m) x m)) xs))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-maximum-value
  (is (= (= (problem-maximum-value 1 8 3 4) 8)))
  (is (= (= (problem-maximum-value 30 20) 30)))
  (is (= (= (problem-maximum-value 45 67 11) 67))))

(run-tests)
