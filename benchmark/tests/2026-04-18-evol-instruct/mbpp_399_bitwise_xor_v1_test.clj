(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0] (bitwise-xor-flags [true false true] [false false true])))
  (is (= [0 1] (bitwise-xor-flags [1 nil 3] [nil 2 3])))
  (is (= [1 3] (bitwise-xor-flags [0 1 0 1] [0 0 0 0]))))

(run-test test-variation)
