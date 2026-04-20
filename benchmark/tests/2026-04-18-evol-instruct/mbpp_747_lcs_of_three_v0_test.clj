(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "fl" (longest-common-prefix "flower" "flow" "flight")))
  (is (= "abc" (longest-common-prefix "abc" "abc" "abc")))
  (is (= "" (longest-common-prefix nil "abc" "ab"))))

(run-test test-variation)
