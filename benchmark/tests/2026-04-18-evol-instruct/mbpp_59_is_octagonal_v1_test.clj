(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 8 21 40 65] (vec (take 5 (octagonal-seq)))))
  (is (= 40 (nth (octagonal-seq) 3))))

(run-test test-variation)
