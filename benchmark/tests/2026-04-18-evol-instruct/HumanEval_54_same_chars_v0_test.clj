(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (same_keys {:a 1, :b 2, :c 3} {:c 9, :b 0, :a 42})))
  (is (= false (same_keys {:a 1, :b 2} {:a 10, :b 20, :c 30})))
  (is (= true (same_keys {} {})))
  (is (= false (same_keys nil {:a 1}))))

(run-test test-variation)
