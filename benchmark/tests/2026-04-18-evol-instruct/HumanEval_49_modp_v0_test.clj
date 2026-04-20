(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (modp 0)))
  (is (= 1 (modp 1)))
  (is (= 3 (modp 7)))
  (is (= 5 (modp 10)))
  (is (= 5 (modp 20))))

(run-test test-variation)
