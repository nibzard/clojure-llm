(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (same_elements [1 2 2 3] '(3 1 1 2))))
  (is (= true (same_elements #{:a :b} [:b :a :a])))
  (is (= true (same_elements nil [])))
  (is (= false (same_elements [1 2 3] [1 2 4]))))

(run-test test-variation)
