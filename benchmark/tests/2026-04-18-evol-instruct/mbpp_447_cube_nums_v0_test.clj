(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["2^3=8" "3^3=27"] (doall (cube-strings [2 3])))))

(run-test test-variation)
