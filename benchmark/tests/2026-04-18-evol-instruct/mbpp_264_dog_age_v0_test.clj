(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:dog-years 21 :cat-years 18} (dog-age 3))))

(run-test test-variation)
