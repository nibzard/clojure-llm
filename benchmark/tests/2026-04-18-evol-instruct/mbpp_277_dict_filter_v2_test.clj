(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[:b 3] [:c 2]] (doall (dict_filter_lazy {:a 1 :b 3 :c 2} 2))))
  (is (= [[:b 4]] (doall (dict_filter_lazy (sorted-map :a 1 :b 4 :c 2) 3)))))

(run-test test-variation)
