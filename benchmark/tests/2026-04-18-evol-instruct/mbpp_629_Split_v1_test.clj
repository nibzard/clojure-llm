(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["a" "c" "e"] (even-indices ["a" "b" "c" "d" "e"])))
  (is (= [1 3 5] (even-indices '(1 2 3 4 5 6))))
  (is (= [] (even-indices []))))

(run-test test-variation)
