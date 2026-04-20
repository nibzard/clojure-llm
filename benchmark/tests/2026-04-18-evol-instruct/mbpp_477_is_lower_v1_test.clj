(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"a" 1, "b" 2, :C 3}
         (lowercase-keys {"A" 1, "B" 2, :C 3}))))

(run-test test-variation)
