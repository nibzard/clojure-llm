(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3 4 1] (rotate-left [1 2 3 4] 1)))
  (is (= [4 1 2 3] (rotate-left [1 2 3 4] -1)))
  (is (= [3 4 1 2] (rotate-left [1 2 3 4] 2)))
  (is (= [] (rotate-left [] 3))))

(run-test test-variation)
