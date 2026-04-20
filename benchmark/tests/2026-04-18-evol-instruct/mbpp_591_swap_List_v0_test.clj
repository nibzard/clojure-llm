(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 2 3 1] (swap-vec-ends [1 2 3 4])))
  (is (= [:b :a] (swap-vec-ends [:a :b])))
  (is (= [42] (swap-vec-ends [42]))))

(run-test test-variation)
