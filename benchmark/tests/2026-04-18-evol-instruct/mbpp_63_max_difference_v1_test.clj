(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (max-difference-pairs [1 5 3])))
  (is (= 12 (max-difference-pairs [-2 10 7])))
  (is (= nil (max-difference-pairs [42]))))

(run-test test-variation)
