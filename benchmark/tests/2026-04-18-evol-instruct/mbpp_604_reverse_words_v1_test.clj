(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {1 :a, 2 :b} (reverse-kv {:a 1, :b 2})))
  (is (= {} (reverse-kv nil)))
  (is (= {1 :b} (reverse-kv {:a 1, :b 1}))))

(run-test test-variation)
