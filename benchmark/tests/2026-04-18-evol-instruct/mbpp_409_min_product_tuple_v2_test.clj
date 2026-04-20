(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 10 (min_product_infinite [3 7 2 5])))
  (is (= 2 (min_product_infinite (range 1 1000000))))
  (is (= nil (min_product_infinite [4]))))

(run-test test-variation)
