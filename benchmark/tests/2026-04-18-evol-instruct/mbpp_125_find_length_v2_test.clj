(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-zero-one-diff [0 1 0 0 1])))
  (is (= -1 (max-zero-one-diff [1 1 1])))
  (is (= nil (max-zero-one-diff []))))

(run-test test-variation)
