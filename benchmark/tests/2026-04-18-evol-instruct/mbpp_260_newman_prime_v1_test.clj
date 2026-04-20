(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1] (nth-pascal-row 0)))
  (is (= [1 1] (nth-pascal-row 1)))
  (is (= [1 4 6 4 1] (nth-pascal-row 4))))

(run-test test-variation)
