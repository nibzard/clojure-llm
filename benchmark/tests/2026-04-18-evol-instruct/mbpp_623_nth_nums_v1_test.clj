(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 9 16] (vec (nth-positive [1 -2 3 nil 4] 2))))
  (is (= [1 8 27] (take 3 (nth-positive (range) 3))))
  (is (= [] (vec (nth-positive [nil :x -1 0] 2)))))

(run-test test-variation)
