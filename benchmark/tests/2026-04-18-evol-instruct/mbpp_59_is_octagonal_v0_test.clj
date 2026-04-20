(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 40 (octagonal-at [1 8 21 40 65] 4))))

(run-test test-variation)
