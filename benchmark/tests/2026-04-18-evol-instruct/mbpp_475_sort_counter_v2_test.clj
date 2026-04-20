(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[:a 1] [:b 2] [:c 2]]
         (sort-by-value-desc {:b 2 :a 1 :c 2}))))

(run-test test-variation)
