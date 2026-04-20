(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 4] (vector-subset-sum [1 2 3 4] 5)))
  (is (= nil (vector-subset-sum [2 4 6] 5)))
  (is (= [] (vector-subset-sum [] 0))))

(run-test test-variation)
