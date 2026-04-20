(defn nth-nums
  "Return a lazy sequence of the n-th powers of the numbers in `nums`.

  Works with finite or infinite sequences and preserves laziness.

  Examples:
  (take 4 (nth-nums [1 2 3 4] 2)) ;=> (1 4 9 16)
  (take 3 (nth-nums (range) 3))   ;=> (0 1 8)"
  [nums n]
  (map #(Math/pow % n) nums))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1.0 4.0 9.0 16.0] (vec (nth-nums [1 2 3 4] 2)))))

(run-test test-variation)
