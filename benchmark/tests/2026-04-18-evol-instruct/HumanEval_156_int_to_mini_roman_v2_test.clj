(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["xix" "clii" "cdxxvi"] (vec_to_mini_roman [19 152 426])))
  (is (= ["i" "iv" "ix" "x"] (vec_to_mini_roman [1 4 9 10])))
  (is (= ["m"] (vec_to_mini_roman [1000]))))

(run-test test-variation)
