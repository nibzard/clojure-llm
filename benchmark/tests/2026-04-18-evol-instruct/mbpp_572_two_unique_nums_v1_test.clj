(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 2 4 1] (unique-preserving-last [1 2 3 2 4 1])))
  (is (= [] (unique-preserving-last nil)))
  (is (= ["a" "c" "b"] (unique-preserving-last '("a" "b" "a" "c" "b")))))

(run-test test-variation)
