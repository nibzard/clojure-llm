(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 :a :missing "x" 4] (solve [1 :a nil "x" 2]))))

(run-test test-variation)
