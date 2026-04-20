(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (min-of-k [3 1 4 2])))
  (is (= nil (min-of-k [])))
  (is (= -5 (min-of-k [10 -5 7 0 -2]))))

(run-test test-variation)
