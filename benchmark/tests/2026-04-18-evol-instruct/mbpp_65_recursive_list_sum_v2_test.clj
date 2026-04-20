(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (nested-product [1 [2 nil] {:a 3}])))
  (is (= 24 (nested-product {:x 2, :y [3 4]})))
  (is (= 1 (nested-product []))))

(run-test test-variation)
