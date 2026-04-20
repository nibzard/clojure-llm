(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (nth-nonconsecutive 0)))
  (is (= 1 (nth-nonconsecutive 1)))
  (is (= 2 (nth-nonconsecutive 2)))
  (is (= 4 (nth-nonconsecutive 3)))
  (is (= 7 (nth-nonconsecutive 5)))
  (is (= nil (nth-nonconsecutive nil))))

(run-test test-variation)
