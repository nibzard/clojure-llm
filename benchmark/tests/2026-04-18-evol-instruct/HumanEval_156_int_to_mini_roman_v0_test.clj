(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "iv" (int_to_mini_roman 4)))
  (is (= "cmxliv" (int_to_mini_roman 944)))
  (is (= "mmmcmxcix" (int_to_mini_roman 3999)))
  (is (= "lxxxviii" (int_to_mini_roman 88))))

(run-test test-variation)
