(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (longest-pal-prefix "abac")))
  (is (= 4 (longest-pal-prefix [1 2 2 1 9])))
  (is (= 0 (longest-pal-prefix nil))))

(run-test test-variation)
