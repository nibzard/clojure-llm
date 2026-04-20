(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (decagonal-index 1)))
  (is (= 2 (decagonal-index 17)))
  (is (= 3 (decagonal-index 32)))
  (is (= nil (decagonal-index 7))))

(run-test test-variation)
