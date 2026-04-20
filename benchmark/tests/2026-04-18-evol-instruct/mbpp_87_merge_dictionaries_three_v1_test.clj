(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 3, :b 2} (merge-dictionaries-variadic {:a 1} {:b 2} {:a 3})))
  (is (= {} (merge-dictionaries-variadic)))
  (is (= {:x 10 :y 20} (merge-dictionaries-variadic nil {:x 10} {:y 20}))))

(run-test test-variation)
