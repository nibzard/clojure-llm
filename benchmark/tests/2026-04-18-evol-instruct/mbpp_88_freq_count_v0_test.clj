(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {1 1, 2 2, 3 1} (freq-by count ["a" "bb" "ccc" "dd"]))))

(run-test test-variation)
