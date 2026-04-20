(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "0b11" (rounded_avg [1 5])))
  (is (= "0b11010" (rounded_avg [10 20 33])))
  (is (= -1 (rounded_avg [])))
  (is (= -1 (rounded_avg [2 nil 4]))))

(run-test test-variation)
