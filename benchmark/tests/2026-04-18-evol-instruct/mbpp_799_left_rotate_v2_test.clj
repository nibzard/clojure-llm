(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 4 5 1 2] (left-rotate-vector [1 2 3 4 5] 2)))
  (is (= [5 1 2 3 4] (left-rotate-vector [1 2 3 4 5] -1)))
  (is (= [] (left-rotate-vector [] 3))))

(run-test test-variation)
