(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 198 (ascii-sum "ABC")))
  (is (= 97 (ascii-sum "a")))
  (is (= 0 (ascii-sum nil))))

(run-test test-variation)
