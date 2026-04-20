(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (decagonal-index 1)))
  (is (= 2 (decagonal-index 8)))
  (is (= 3 (decagonal-index 25)))
  (is (= nil (decagonal-index 26))))

(run-test test-variation)
