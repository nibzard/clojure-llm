(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 6 24] (doall (running-products [1 2 3 4])))))

(run-test test-variation)
