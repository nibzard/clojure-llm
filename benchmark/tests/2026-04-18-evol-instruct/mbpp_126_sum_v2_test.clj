(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 6 10] (vec (prefix-sum [1 2 3 4]))))
  (is (= [] (vec (prefix-sum nil))))
  (is (= [0 1 3] (vec (take 3 (prefix-sum (range)))))))

(run-test test-variation)
