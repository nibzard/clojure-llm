(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (first-positive-digit [0 -3 0 7 2])))
  (is (= nil (first-positive-digit [0 0 0])))
  (is (= 1 (first-positive-digit [\a \1 \b \2]))))

(run-test test-variation)
