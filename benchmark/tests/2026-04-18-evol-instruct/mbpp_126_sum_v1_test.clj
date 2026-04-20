(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 2 3 6) (common-divisors 12 18)))
  (is (= '(1) (common-divisors 7 13)))
  (is (= '() (common-divisors nil 10))))

(run-test test-variation)
