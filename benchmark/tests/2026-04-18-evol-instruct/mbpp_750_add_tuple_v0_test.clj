(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [11 22 33] (add-nth-elements [1 2 3] [10 20 30]))))

(run-test test-variation)
