(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [5 3 3 2 2 1] (rolling-min [5 3 4 2 6 1])))
  (is (= [10 8 8 7 7] (take 5 (rolling-min [10 8 9 7 11 6 12]))))
  (is (= nil (rolling-min nil))))

(run-test test-variation)
