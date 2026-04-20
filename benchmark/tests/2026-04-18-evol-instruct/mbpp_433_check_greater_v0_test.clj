(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (all-greater? [5 6 7] 4)))
  (is (= false (all-greater? [1 5 7] 4)))
  (is (= true (all-greater? [] 10))))

(run-test test-variation)
