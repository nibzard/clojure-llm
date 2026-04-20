(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is-strictly-divisible-by-11? 22))))

(run-test test-variation)
