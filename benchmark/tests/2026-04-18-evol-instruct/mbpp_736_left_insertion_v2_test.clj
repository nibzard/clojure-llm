(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (right-insertion [1 2 2 2 3] 2)))
  (is (= 2 (right-insertion [1 3 5] 4)))
  (is (= 0 (right-insertion [] 10))))

(run-test test-variation)
