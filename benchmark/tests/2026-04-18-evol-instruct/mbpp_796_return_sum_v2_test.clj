(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (return-sum-values {:a 1 :b 2 :c 3})))
  (is (= 5 (return-sum-values {:a 1 :b nil :c 4})))
  (is (= 0 (return-sum-values {}))))

(run-test test-variation)
