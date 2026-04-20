(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "2,4" (join-when even? "," [1 2 3 4 5]))))

(run-test test-variation)
