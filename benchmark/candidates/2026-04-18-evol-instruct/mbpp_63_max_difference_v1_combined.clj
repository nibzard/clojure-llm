(defn max-difference-pairs
  "Return the maximum absolute difference between any two numbers in the vector.
  If the vector has fewer than 2 elements, return nil.

  Examples:
  (max-difference-pairs [1 5 3]) => 4
  (max-difference-pairs [-2 10 7]) => 12
  (max-difference-pairs [42]) => nil"
  [nums]
  (when (and nums (seq (rest nums)))
    (- (apply max nums) (apply min nums))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (max-difference-pairs [1 5 3])))
  (is (= 12 (max-difference-pairs [-2 10 7])))
  (is (= nil (max-difference-pairs [42]))))

(run-test test-variation)
