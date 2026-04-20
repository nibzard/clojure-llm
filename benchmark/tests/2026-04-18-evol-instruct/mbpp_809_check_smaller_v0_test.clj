(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (every-prefix-smaller? [3 5 10] [1 4 8])))
  (is (= false (every-prefix-smaller? [3 5 10] [1 4 9])))
  (is (= true (every-prefix-smaller? [] []))))

(run-test test-variation)
