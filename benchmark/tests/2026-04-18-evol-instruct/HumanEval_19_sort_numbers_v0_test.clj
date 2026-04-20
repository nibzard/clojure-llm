(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '([1 :a] [2 :b] [3 :c])
         (sort-numbers-by-key [[3 :c] [1 :a] [2 :b]])))
  (is (= '([1 "z"] [2 "b"] [2 "a"])
         (sort-numbers-by-key [[2 "b"] [2 "a"] [1 "z"]]))))

(run-test test-variation)
