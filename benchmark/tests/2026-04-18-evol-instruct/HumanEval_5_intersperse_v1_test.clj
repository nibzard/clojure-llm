(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1, :b {:c 2}, :d [{:e 3}]}
         (map-keys-deep keyword {"a" 1, "b" {"c" 2}, :d [{"e" 3}]}))))

(run-test test-variation)
