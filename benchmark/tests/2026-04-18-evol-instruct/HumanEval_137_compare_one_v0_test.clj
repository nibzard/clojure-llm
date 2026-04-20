(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "fl" (longest-common-prefix ["flower" "flow" "flight"])))
  (is (= "" (longest-common-prefix ["dog" "racecar" "car"])))
  (is (= "" (longest-common-prefix ["interspecies" nil "interstellar"])))
  (is (= "" (longest-common-prefix []))))

(run-test test-variation)
