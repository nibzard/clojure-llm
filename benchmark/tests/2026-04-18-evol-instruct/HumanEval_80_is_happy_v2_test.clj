(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is_happy? [1 2 3])))
  (is (= false (is_happy? [1 1 2])))
  (is (= true (is_happy? '(a b c d))))
  (is (= true (is_happy? (range 5))))
  (is (= false (is_happy? [1 2]))))

(run-test test-variation)
