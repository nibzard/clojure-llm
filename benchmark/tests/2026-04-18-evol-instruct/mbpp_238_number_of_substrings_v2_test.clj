(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (count-distinct-subsequences "abc")))
  (is (= 3 (count-distinct-subsequences "aaa")))
  (is (= 0 (count-distinct-subsequences nil))))

(run-test test-variation)
