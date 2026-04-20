(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (sum-numeric-values {:a 1 :b 2 :c 3})))
  (is (= 1 (sum-numeric-values {:a 1 :b "x" :c nil})))
  (is (= 0 (sum-numeric-values {}))))

(run-test test-variation)
