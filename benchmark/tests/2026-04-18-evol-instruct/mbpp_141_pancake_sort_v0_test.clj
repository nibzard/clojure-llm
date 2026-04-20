(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("b" "d" "aa" "cc")
         (stable_by count ["aa" "b" "cc" "d"]))))

(run-test test-variation)
