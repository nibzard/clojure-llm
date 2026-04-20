(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (check-longer [1 22 "a"] [10 333 "zz"]))))

(run-test test-variation)
