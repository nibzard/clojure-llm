(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1 :b {:c 2 :d 3} :e 4}
         (merge-nested-maps {:a 1 :b {:c 2}} {:b {:d 3} :e 4})))
  (is (= {:a 1} (merge-nested-maps nil {:a 1})))
  (is (= {:a 2 :b 3}
         (merge-nested-maps {:a 1 :b 2} {:a 2}))))

(run-test test-variation)
