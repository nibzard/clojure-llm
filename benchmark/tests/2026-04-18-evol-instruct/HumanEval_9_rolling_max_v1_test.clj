(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0 1 2 2 2 5 5] (rolling-max-indexes [1 2 3 2 3 4 2])))
  (is (= [0 0 0 3 3] (rolling-max-indexes [5 5 1 6 6])))
  (is (= [] (rolling-max-indexes []))))

(run-test test-variation)
