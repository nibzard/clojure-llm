(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \1 (first-digit-in-string "ab12cd"))))

(run-test test-variation)
