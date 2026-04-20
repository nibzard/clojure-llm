(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (upper-ctr-nested ["Ab" ["Cde" nil] "FG"]))))

(run-test test-variation)
