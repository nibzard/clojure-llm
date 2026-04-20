(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {3 3} (histogram [1 2 2 3 3 3])))
  (is (= {1 2, 2 2} (histogram [1 1 2 2 3])))
  (is (= {4 2} (histogram [nil 4 4 nil 5])))
  (is (= {} (histogram []))))

(run-test test-variation)
