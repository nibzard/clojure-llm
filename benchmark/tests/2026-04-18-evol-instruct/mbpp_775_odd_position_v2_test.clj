(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (odd-position? [2 3 4 5])))
  (is (= false (odd-position? [1 2 3 4])))
  (is (= true (odd-position? []))))

(run-test test-variation)
