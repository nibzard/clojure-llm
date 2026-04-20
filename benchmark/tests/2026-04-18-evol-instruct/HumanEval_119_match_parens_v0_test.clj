(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (longest-balanced-prefix "()())")))

(run-test test-variation)
