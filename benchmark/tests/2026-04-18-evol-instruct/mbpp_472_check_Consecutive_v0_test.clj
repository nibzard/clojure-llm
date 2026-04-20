(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3 1] (consecutive-runs [1 1 2 2 2 3])))
  (is (= [3 2] (consecutive-runs "aaabb")))
  (is (= [] (consecutive-runs [])))
  (is (= [] (consecutive-runs nil))))

(run-test test-variation)
