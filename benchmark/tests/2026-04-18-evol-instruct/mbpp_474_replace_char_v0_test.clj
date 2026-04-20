(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [9 2 9 3] (replace-elements [1 2 1 3] 1 9)))
  (is (= [] (replace-elements [] :a :b)))
  (is (= [:x 2 :x] (replace-elements [nil 2 nil] nil :x))))

(run-test test-variation)
