(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [100 87 0 0 43] (normalize-scores [120 87 nil -5 42.6])))
  (is (= [10 50 100] (normalize-scores '(10 50 99.9)))))

(run-test test-variation)
