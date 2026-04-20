(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-set-bits-in-binary-string "10101")))
  (is (= 2 (count-set-bits-in-binary-string 10)))
  (is (= 0 (count-set-bits-in-binary-string nil)))
  (is (= 4 (count-set-bits-in-binary-string "11110000"))))

(run-test test-variation)
