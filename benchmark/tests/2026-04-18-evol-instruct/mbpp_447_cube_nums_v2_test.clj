(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 8 27) (cube-transduce [1 2 3])))
  (is (= '(-8 0 64) (cube-transduce [nil -2 0 4])))
  (is (= '(0 1 8 27) (take 4 (cube-transduce (range))))))

(run-test test-variation)
