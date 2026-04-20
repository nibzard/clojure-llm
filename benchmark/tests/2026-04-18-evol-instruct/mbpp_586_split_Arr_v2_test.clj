(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 1 2 3] (rotate-right [1 2 3 4] 1)))
  (is (= [2 3 4 1] (rotate-right [1 2 3 4] 3)))
  (is (= [2 3 4 1] (rotate-right [1 2 3 4] -1))))

(run-test test-variation)
