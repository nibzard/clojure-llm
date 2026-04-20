(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["a" "bb" "cc" "ee" "ddd"]
         (stable-sort-by-length ["bb" "a" "cc" "ddd" "ee"]))))

(run-test test-variation)
