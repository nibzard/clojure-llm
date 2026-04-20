(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 4] (remove-kth-assoc [1 2 3 4] 2))))

(run-test test-variation)
