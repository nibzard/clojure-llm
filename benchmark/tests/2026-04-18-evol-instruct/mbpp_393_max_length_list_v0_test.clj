(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "bbb" (longest-by count ["a" "bbb" "cc"]))))

(run-test test-variation)
