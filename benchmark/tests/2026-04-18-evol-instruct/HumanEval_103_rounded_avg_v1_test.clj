(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "0b11" (median_binary 1 5)))
  (is (= "0b11" (median_binary 1 4)))
  (is (= -1 (median_binary 7 5)))
  (is (= "0b1111" (median_binary 10 20))))

(run-test test-variation)
