(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (square-free? 10)))
  (is (= false (square-free? 12)))
  (is (= true (square-free? 1)))
  (is (= false (square-free? nil))))

(run-test test-variation)
