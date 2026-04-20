(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 13 (digit-distance-strings "314" "159"))))

(run-test test-variation)
