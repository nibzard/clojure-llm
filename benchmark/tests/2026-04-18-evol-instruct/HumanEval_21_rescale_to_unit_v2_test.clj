(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 0.0, :b 0.5, :c 1.0}
         (rescale-map-values {:a 10 :b 20 :c 30}))))

(run-test test-variation)
