(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 3) (occurrence-indices [1 2 3 2 3 4] [2 3])))
  (is (= '(0 7) (occurrence-indices "abracadabra" "abra")))
  (is (= nil (occurrence-indices [1 2 3] [4]))))

(run-test test-variation)
