(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "iv" (int_to_mini_roman 4)))
  (is (= "xix" (int_to_mini_roman 19)))
  (is (= "cmxliv" (int_to_mini_roman 944))))

(run-test test-variation)
