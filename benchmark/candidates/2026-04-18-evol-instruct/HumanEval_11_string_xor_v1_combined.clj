(defn vector_xor
  "Given two vectors of booleans a and b, return a vector where each element is the XOR of the corresponding elements.
  The vectors may be different lengths; treat missing values as false.
  Examples:
  (vector_xor [true false true] [false false true])
  => [true false false]

  (vector_xor [true true] [false true false])
  => [true false true]"
  [a b]
  (vec (map #(boolean (not= %1 %2)) (concat a (repeat false)) (concat b (repeat false)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [true false false] (vector_xor [true false true] [false false true])))
  (is (= [true false true] (vector_xor [true true] [false true false]))))

(run-test test-variation)
