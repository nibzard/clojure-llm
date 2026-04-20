(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "0110" (xor-bits "1010" "1100")))
  (is (= "1111" (xor-bits "0000" "1111")))
  (is (= "" (xor-bits "" ""))))

(run-test test-variation)
