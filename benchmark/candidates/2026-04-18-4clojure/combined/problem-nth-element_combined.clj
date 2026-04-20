(defn problem-nth-element [a b]
  (if (zero? b)
    (first a)
    (recur (rest a) (dec b))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-nth-element
  (is (= (= (problem-nth-element '(4 5 6 7) 2) 6)))
  (is (= (= (problem-nth-element [:a :b :c] 0) :a)))
  (is (= (= (problem-nth-element [1 2 3 4] 1) 2)))
  (is (= (= (problem-nth-element '([1 2] [3 4] [5 6]) 2) [5 6]))))

(run-tests)
