(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 2] (change-base-seq 8 3))))

(run-test test-variation)
