(defn problem-flatten-a-sequence [a]
  (letfn [(flat [x]
            (if (sequential? x)
              (mapcat flat x)
              (list x)))]
    (flat a)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-flatten-a-sequence
  (is (= (= (problem-flatten-a-sequence '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))))
  (is (= (= (problem-flatten-a-sequence ["a" ["b"] "c"]) '("a" "b" "c"))))
  (is (= (= (problem-flatten-a-sequence '((((:a))))) '(:a)))))

(run-tests)
