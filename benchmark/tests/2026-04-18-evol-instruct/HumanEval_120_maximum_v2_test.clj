(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3 5] (top-distinct [3 1 3 2 2 5] 3)))
  (is (= [4] (top-distinct [4 4 4] 2)))
  (is (= [] (top-distinct nil 5)))
  (is (= [] (top-distinct [9 8 7] 0))))

(run-test test-variation)
