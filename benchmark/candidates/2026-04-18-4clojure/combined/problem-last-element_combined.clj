(defn problem-last-element [a]
  (if (next a)
    (recur (next a))
    (first a)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-last-element
  (is (= (= (problem-last-element [1 2 3 4 5]) 5)))
  (is (= (= (problem-last-element '(5 4 3)) 3)))
  (is (= (= (problem-last-element ["b" "c" "d"]) "d"))))

(run-tests)
