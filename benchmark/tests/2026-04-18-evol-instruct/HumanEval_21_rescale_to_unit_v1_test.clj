(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0.0 0.3333333333333333 0.6666666666666666 1.0]
         (normalize-range [2 4 6 8])))
  (is (= [0.0 0.0 0.0]
         (normalize-range [5 5 5]))))

(run-test test-variation)
