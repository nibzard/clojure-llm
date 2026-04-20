(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {\1 1, \0 3, \2 2, \3 2} (solve [10 202 nil -33]))))

(run-test test-variation)
