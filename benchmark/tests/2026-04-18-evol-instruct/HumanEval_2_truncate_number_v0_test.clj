(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "5" (decimal-digits 3.5)))
  (is (= "25" (decimal-digits 3.25)))
  (is (= "0" (decimal-digits 7)))
  (is (= "05" (decimal-digits 1.05))))

(run-test test-variation)
