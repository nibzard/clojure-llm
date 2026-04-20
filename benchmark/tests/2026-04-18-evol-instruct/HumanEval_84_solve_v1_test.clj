(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [0 1 1] (solve 6))))

(run-test test-variation)
