(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 2) (longest-nonempty-list [nil '() '(1) '(1 2) '(3 4)])))
  (is (= nil (longest-nonempty-list ['() nil])))
  (is (= '(a b c) (longest-nonempty-list ['(a) '(a b) nil '(a b c) '(x y z)]))))

(run-test test-variation)
