(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (common-type? [1 2 3])))
  (is (= true (common-type? [1 nil 2])))
  (is (= false (common-type? [1 "a" 3]))))

(run-test test-variation)
