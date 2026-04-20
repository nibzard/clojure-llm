(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 1 3] (min-width-sublist [1 2 1 3 2])))
  (is (= ["a" "b" "a"] (min-width-sublist ["a" "b" "a"]))))

(run-test test-variation)
