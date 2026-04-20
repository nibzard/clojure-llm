(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (second-smallest-distinct [3 1 2 1]))))

(run-test test-variation)
