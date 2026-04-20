(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "0b10" (safe-rounded-avg [1 2 3 4])))
  (is (= "0b11" (safe-rounded-avg [1 nil 5])))
  (is (= nil (safe-rounded-avg []))))

(run-test test-variation)
