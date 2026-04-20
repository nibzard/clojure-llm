(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3] [2 2] [3 1]] (run-length-encode [1 1 1 2 2 3])))
  (is (= [[\a 3] [\b 2] [\c 1]] (run-length-encode "aaabbc")))
  (is (= [] (run-length-encode nil))))

(run-test test-variation)
