(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1.0 4.0 9.0 16.0] (vec (nth-nums [1 2 3 4] 2)))))

(run-test test-variation)
