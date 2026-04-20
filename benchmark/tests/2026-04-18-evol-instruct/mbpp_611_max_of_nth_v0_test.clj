(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (max-of-nth [[1 2 3] [4 5 6] [7 8 9]] 1)))
  (is (= 20 (max-of-nth '([10 20] [3] [7 15]) 1)))
  (is (= nil (max-of-nth [[1] [] [2]] 1))))

(run-test test-variation)
