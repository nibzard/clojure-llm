(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12 (max-product-pair [1 2 3 4])))
  (is (= 50 (max-product-pair [-10 5 2])))
  (is (= nil (max-product-pair nil))))

(run-test test-variation)
