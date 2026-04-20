(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is-monotonic? nil)))
  (is (= true (is-monotonic? [])))
  (is (= true (is-monotonic? [1 1 2 3])))
  (is (= true (is-monotonic? '(5 4 4 1))))
  (is (= false (is-monotonic? [1 3 2]))))

(run-test test-variation)
