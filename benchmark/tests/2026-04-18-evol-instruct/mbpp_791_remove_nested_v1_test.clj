(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 4 :a] (remove_nested [1 [2 3] 4 [5] :a]))))

(run-test test-variation)
