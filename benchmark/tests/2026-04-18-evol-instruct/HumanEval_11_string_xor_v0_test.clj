(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= (byte-array [5 7 5])
         (xor-bytes (byte-array [1 2 3]) (byte-array [4 5 6]))))
  (is (= (byte-array [])
         (xor-bytes nil (byte-array [1 2]))))
  (is (= (byte-array [7])
         (xor-bytes (byte-array [2 9]) (byte-array [5])))))

(run-test test-variation)
