(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (equal-shape? [[1 2] [:a :b] '(x y)])))
  (is (= false (equal-shape? [[1] [2 3]])))
  (is (= true (equal-shape? [])))
  (is (= true (equal-shape? [nil []]))))

(run-test test-variation)
