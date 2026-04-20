(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 11 :b 1 :c 5} (add-map-values {:a 1 :b 2} {:a 10 :b -1 :c 5})))
  (is (= {:a 3} (add-map-values nil {:a 3})))
  (is (= {:x 4} (add-map-values {:x 4} nil))))

(run-test test-variation)
