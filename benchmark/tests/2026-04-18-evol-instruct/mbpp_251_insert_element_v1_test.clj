(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:x 1 :x 2 :x 3] (insert-element-into-vector [1 2 3] :x))))

(run-test test-variation)
