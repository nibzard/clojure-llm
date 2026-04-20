(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["a" "d" "bb" "cc"]
         (stable-sort-by count ["bb" "a" "cc" "d"]))))

(run-test test-variation)
