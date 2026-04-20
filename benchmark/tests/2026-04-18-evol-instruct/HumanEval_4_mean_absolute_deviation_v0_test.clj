(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1.0 1.5 2.0 2.5] (doall (running-average [1 2 3 4]))))
  (is (= [0.0 0.5 1.0] (take 3 (running-average (range)))))

(run-test test-variation)
