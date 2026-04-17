(require '[clojure.test :refer [deftest is run-test]])

(def candidate multiply_elements)

(deftest test-humaneval

  (is (= (candidate [1 5 7 8 10]) [5 35 56 80]))
  (is (= (candidate [2 4 5 6 7]) [8 20 30 42]))
  (is (= (candidate [12 13 14 9 15]) [156 182 126 135]))
  (is (= (candidate [12]) []))
)

(run-test test-humaneval)