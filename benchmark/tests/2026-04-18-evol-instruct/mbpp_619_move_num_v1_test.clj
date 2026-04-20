(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:a "x" 1 2 3] (move-num [1 :a 2 "x" 3]))))

(run-test test-variation)
