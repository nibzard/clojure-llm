(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [11 22 3] (merge-vectors-by-index [1 2 3] [10 20] +)))
  (is (= ["110" 20 30] (merge-vectors-by-index [1] [10 20 30] str))))

(run-test test-variation)
