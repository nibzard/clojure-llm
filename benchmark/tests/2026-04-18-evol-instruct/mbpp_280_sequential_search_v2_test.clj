(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [true 3] (sequential_search [1 3 5 8] even?)))
  (is (= [false -1] (sequential_search [1 3 5 7] even?)))
  (is (= [true 2] (sequential_search (range) #(= 2 %)))))

(run-test test-variation)
