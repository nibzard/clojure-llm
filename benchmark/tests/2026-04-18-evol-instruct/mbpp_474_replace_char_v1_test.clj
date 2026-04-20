(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [9 2 9 3] (replace-elements [1 2 1 3] 1 9))))

(run-test test-variation)
