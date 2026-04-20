(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 2] (min-product-pair [[3 4] [1 10] [2 2]])))
  (is (= [-3 1] (min-product-pair [[-3 1] [2 2] [4 5]])))
  (is (= nil (min-product-pair []))))

(run-test test-variation)
