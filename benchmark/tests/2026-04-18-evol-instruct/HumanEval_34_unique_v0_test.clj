(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [5 3 2 9 0 123] (unique-by identity [5 3 5 2 3 3 9 0 123]))))

(run-test test-variation)
