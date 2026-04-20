(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 16 (sum-divisors-of-coll 12 [1 2 3 4 5 6]))))

(run-test test-variation)
