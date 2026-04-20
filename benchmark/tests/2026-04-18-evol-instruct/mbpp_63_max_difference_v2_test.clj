(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (max-cumulative-drop [10 7 5 8 3]))))

(run-test test-variation)
