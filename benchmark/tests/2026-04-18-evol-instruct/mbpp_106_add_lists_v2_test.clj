(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1 :b {:c 2 :d 3} :e 4}
         (merge-maps-deep {:a 1 :b {:c 2}} {:b {:d 3} :e 4})))
  (is (= {:x 1}
         (merge-maps-deep nil {:x 1})))
  (is (= {:a {:b 2}}
         (merge-maps-deep {:a {:b 1}} {:a {:b 2}}))))

(run-test test-variation)
