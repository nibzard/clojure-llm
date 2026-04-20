(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 3 3 2 2 1] (sort-by-frequency [3 1 2 2 3 3])))
  (is (= [] (sort-by-frequency nil)))
  (is (= [] (sort-by-frequency []))))

(run-test test-variation)
