(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (nested-map-sum {:a 1 :b 2})))
  (is (= 6 (nested-map-sum {:a 1 :b {:c 2 :d 3} :e "x"})))
  (is (= 0 (nested-map-sum nil))))

(run-test test-variation)
