(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-decimals [1.23 4.5 6.789 "8.90" nil]))))

(run-test test-variation)
