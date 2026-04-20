(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3] (strip-nested-maps [1 {:a 2} 3 {:b 4}])))
  (is (= [] (strip-nested-maps [])))
  (is (= [] (strip-nested-maps nil))))

(run-test test-variation)
