(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (median-range [1 3 2])))
  (is (= 2.5 (median-range [1 2 3 4])))
  (is (= 2 (median-range (take 5 (range))))))

(run-test test-variation)
