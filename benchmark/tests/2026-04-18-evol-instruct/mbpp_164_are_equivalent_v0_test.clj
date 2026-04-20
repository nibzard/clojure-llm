(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (common-prefix? [1 2 3] [1 9 8])))
  (is (= true (common-prefix? '("a" "b") '("a" "c"))))
  (is (= false (common-prefix? [] [1 2])))
  (is (= false (common-prefix? nil [1 2]))))

(run-test test-variation)
