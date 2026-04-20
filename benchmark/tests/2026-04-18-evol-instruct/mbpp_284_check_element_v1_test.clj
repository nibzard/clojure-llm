(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (check-element-prefix ["cl" "cl" "cl"] "cl")))
  (is (= false (check-element-prefix ["cl" "clj"] "cl")))
  (is (= true (check-element-prefix nil "x"))))

(run-test test-variation)
