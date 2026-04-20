(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(:x 1 :x 2 :x 3)
         (take 6 (interleave-element [1 2 3] :x))))
  (is (= '(0 0 0 1 0)
         (take 5 (interleave-element (range) 0))))

(run-test test-variation)
