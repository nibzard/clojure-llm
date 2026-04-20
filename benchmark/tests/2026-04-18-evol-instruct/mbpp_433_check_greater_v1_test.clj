(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-greater [1 5 3 9] 4)))
  (is (= 0 (count-greater [] 10)))
  (is (= 0 (count-greater nil 2))))

(run-test test-variation)
