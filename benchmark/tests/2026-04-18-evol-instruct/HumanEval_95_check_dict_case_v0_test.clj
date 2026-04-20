(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (check-set-case #{"a" "b" "c"})))
  (is (= true (check-set-case #{"A" "B" "C"})))
  (is (= false (check-set-case #{"a" "B"}))))

(run-test test-variation)
