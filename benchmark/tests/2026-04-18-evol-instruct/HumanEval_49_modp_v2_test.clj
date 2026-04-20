(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (modp-safe 3 5)))
  (is (= 2 (modp-safe 1101 101)))
  (is (= 1 (modp-safe 0 101)))
  (is (= nil (modp-safe nil 101)))
  (is (= 1 (modp-safe 1000000 13))))

(run-test test-variation)
