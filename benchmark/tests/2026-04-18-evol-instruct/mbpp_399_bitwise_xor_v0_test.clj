(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (bitwise_xor 5 3)))
  (is (= 6 (bitwise_xor 12 10)))
  (is (= nil (bitwise_xor nil 10))))

(run-test test-variation)
