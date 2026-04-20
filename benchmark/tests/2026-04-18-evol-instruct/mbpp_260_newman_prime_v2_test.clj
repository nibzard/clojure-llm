(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (nth-odd-prime 1)))
  (is (= 13 (nth-odd-prime 5)))
  (is (= nil (nth-odd-prime 0))))

(run-test test-variation)
