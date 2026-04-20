(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [true 2] (sequential-search [1 [2 3] 4] 3))))

(run-test test-variation)
