(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (issorted? nil)))
  (is (= true (issorted? [])))
  (is (= true (issorted? [1 2 2 5])))
  (is (= false (issorted? [3 1 2]))))

(run-test test-variation)
