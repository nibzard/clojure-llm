(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (odd-position-sum? [2 3 4 5])))
  (is (= false (odd-position-sum? [1 2 3 4])))
  (is (= true (odd-position-sum? nil))))

(run-test test-variation)
