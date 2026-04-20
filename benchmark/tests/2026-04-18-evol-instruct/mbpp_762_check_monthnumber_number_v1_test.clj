(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (months-with-30-days? [4 6 9])))
  (is (= false (months-with-30-days? [4 5 6])))
  (is (= true (months-with-30-days? nil))))

(run-test test-variation)
