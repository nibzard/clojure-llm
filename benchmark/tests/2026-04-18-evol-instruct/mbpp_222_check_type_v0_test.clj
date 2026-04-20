(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (same-shape? [[1 2] [3 4]])))
  (is (= false (same-shape? [[1 2] [3 4 5]])))
  (is (= true (same-shape? [[:a {:x 1}] [[:b] {:y 2}]])))
  (is (= true (same-shape? nil))))

(run-test test-variation)
