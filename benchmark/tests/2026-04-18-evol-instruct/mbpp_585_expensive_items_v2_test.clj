(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:books [{:category :books :price 25}]
          :games [{:category :games :price 60}]}
         (expensive_items_by-category
          [{:category :books :price 10}
           {:category :books :price 25}
           {:category :games :price 60}
           {:category :games :price 40}]
          1))))

(run-test test-variation)
