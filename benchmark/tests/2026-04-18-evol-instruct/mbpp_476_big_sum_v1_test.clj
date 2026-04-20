(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 10 (sum-extremes [3 1 9 4])))
  (is (= 10 (sum-extremes [nil [7 2] #{5 1} 8])))
  (is (= nil (sum-extremes []))))

(run-test test-variation)
