(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 -5 10 3] (max-infinite-prefix [1 2 -5 10 3]))))

(run-test test-variation)
