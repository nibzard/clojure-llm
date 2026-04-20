(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 42 (parse-positive-integer "42"))))

(run-test test-variation)
