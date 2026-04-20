(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (count-even-digits [1 2 3 4 5 6])))
  (is (= 2 (count-even-digits [nil 10 "x" 7 8]))))

(run-test test-variation)
