(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(0 3) (occurrence-indices [1 2 3 1 2 4] [1 2])))
  (is (= '(1 3) (occurrence-indices "banana" "ana")))
  (is (= nil (occurrence-indices [1 2 3] [4 5]))))

(run-test test-variation)
