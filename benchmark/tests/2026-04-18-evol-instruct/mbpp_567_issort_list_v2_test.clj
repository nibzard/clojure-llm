(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (sorted-by-length? ["a" "to" "cat"]))))

(run-test test-variation)
