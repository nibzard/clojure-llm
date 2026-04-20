(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 5] (min-product-pair [[3 4] [2 5] [1 9]]))))

(run-test test-variation)
