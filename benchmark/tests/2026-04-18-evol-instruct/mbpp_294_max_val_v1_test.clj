(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "fl" (max-common-prefix ["flower" "flow" "flight"])))
  (is (= "" (max-common-prefix ["dog" "racecar" "car"])))
  (is (= "" (max-common-prefix [])))
  (is (= "" (max-common-prefix [nil "abc"]))))

(run-test test-variation)
