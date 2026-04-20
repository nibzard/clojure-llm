(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[:a 3] [:c 3] [:b 1]]
         (sort-counter-by-count {:a 3 :b 1 :c 3})))
  (is (= [] (sort-counter-by-count nil))))

(run-test test-variation)
