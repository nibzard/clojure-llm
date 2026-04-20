(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (same_keys {:a 1 :b 2} {:b 9 :a 0})))
  (is (= false (same_keys {:a 1 :b 2} {:a 1 :b 2 :c 3})))
  (is (= true (same_keys nil {})))
  (is (= true (same_keys {:a 1 :b nil} {:b 4 :a 7}))))

(run-test test-variation)
