(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0.5 0 1/3 0.75]
         (vec (take 4 (truncate-seq [3.5 2 7/3 nil -4.25]))))))

(run-test test-variation)
