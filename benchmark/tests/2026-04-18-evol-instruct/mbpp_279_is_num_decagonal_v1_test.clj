(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 7 18 34 55) (take 5 (decagonal-seq))))
  (is (= 325 (nth (decagonal-seq) 9))))

(run-test test-variation)
