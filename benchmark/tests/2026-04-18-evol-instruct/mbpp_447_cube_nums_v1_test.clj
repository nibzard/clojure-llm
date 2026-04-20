(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 8 27 64] (vec (cube-nums [1 2 3 4]))))
  (is (= [0 1 8] (vec (take 3 (cube-nums (range)))))))

(run-test test-variation)
