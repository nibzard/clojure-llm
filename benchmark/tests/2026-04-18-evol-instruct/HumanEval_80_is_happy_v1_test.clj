(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_strictly_increasing [1 2 3])))
  (is (= false (is_strictly_increasing [1 1 2])))
  (is (= true (is_strictly_increasing [nil 2 5 9])))
  (is (= false (is_strictly_increasing [3 nil 2])))
  (is (= false (is_strictly_increasing []))))

(run-test test-variation)
