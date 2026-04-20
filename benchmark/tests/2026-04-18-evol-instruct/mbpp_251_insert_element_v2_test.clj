(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 :x 2 3 :x 4] (insert_element [1 2 3 4] :x)))
  (is (= [] (insert_element [] :x)))
  (is (= [:a :z :b] (insert_element [:a :b] :z))))

(run-test test-variation)
