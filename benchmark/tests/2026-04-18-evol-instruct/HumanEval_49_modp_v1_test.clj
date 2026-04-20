(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (modp "3" 5)))
  (is (= 2 (modp " 1101 " 101)))
  (is (= 1 (modp "0" 101)))
  (is (= 1 (modp "100" 101)))
  (is (= nil (modp "12a" 7))))

(run-test test-variation)
