(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:count 3, :sum 6, :avg 2.0}
         (summarize-nums [1 2 3]))))

(run-test test-variation)
