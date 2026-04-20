(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '({:name "b" :price 5} {:name "c" :price 7})
         (cheapest-k [{:name "a" :price 10}
                      {:name "b" :price 5}
                      {:name "c" :price 7}] 2))))

(run-test test-variation)
