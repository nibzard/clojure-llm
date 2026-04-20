(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (common-element? [1 2 3] '(4 5 2))))
  (is (= false (common-element? nil [1 2 3])))
  (is (= true (common-element? (range) [1000]))))

(run-test test-variation)
