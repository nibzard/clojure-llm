(defn cube-nums
  "Return a lazy sequence of cubes for the numbers in `nums`.

  Works with finite or infinite sequences.

  Examples:
  (take 4 (cube-nums [1 2 3 4]))
  ;; => (1 8 27 64)

  (take 3 (cube-nums (range)))
  ;; => (0 1 8)"
  [nums]
  (map #(* % % %) nums))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 8 27 64] (vec (cube-nums [1 2 3 4]))))
  (is (= [0 1 8] (vec (take 3 (cube-nums (range)))))))

(run-test test-variation)
