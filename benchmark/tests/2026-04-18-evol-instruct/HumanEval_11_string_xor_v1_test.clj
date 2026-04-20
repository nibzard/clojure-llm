(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [true false false] (vector_xor [true false true] [false false true])))
  (is (= [true false true] (vector_xor [true true] [false true false]))))

(run-test test-variation)
