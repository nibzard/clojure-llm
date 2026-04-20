(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (median-or-nil [3 1 2])))
  (is (= 6 (median-or-nil '(10 4 8 2))))
  (is (= 3 (median-or-nil [1 nil 5 3])))
  (is (= nil (median-or-nil []))))

(run-test test-variation)
