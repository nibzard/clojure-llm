(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 4 :b {:c 7 :d 7} :e 9}
         (add_nested_maps {:a 1 :b {:c 2}} {:a 3 :b {:c 5 :d 7} :e 9}))))

(run-test test-variation)
