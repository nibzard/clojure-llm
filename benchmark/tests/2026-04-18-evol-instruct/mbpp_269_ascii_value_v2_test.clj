(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 65 (ascii-sum "A")))
  (is (= 294 (ascii-sum "abc")))
  (is (= 0 (ascii-sum nil))))

(run-test test-variation)
