(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 4, :b {:c 2, :d 4}}
         (merge-nested-maps {:a 1 :b {:c 2}} {:a 3 :b {:d 4}})))
  (is (= {:a {:x 6, :y 2}}
         (merge-nested-maps {:a {:x 1}} {:a {:x 5 :y 2}})))
  (is (= {:a 1, :b {:c 2}}
         (merge-nested-maps {:a 1 :b {:c 2}} {}))))

(run-test test-variation)
