(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-zero-one-diff [0 0 1 0 1])))
  (is (= 0 (max-zero-one-diff [1 1 1])))
  (is (= 0 (max-zero-one-diff [])))
  (is (= 3 (max-zero-one-diff [0 0 0 1 1]))))

(run-test test-variation)
