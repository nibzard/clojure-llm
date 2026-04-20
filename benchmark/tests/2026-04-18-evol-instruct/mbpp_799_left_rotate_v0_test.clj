(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 4 5 1 2] (left-rotate-by [1 2 3 4 5] 2)))
  (is (= '(b c d a) (left-rotate-by '(a b c d) 1)))
  (is (= [] (left-rotate-by [] 3)))
  (is (= [3 1 2] (left-rotate-by [1 2 3] -1))))

(run-test test-variation)
