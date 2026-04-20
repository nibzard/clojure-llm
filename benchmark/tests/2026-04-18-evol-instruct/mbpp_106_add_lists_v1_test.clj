(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 4] (merge-vectors [1 2] [3 4]))))

(run-test test-variation)
