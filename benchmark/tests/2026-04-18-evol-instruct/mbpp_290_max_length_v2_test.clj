(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "fl" (longest-prefix ["flower" "flow" "flight"]))))

(run-test test-variation)
