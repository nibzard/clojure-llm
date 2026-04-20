(defn average-of-cubes
  "Return the average of the cubes in `nums`.

  Accepts any seqable collection of numbers, including nil and empty collections.
  Returns 0 for nil or empty input.

  Examples:
  (average-of-cubes [1 2 3]) ;=> 12
  (average-of-cubes nil)     ;=> 0
  (average-of-cubes [])      ;=> 0"
  [nums]
  (let [xs (seq nums)]
    (if xs
      (/ (reduce + (map #(* % % %) xs))
         (count xs))
      0)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12 (average-of-cubes [1 2 3])))
  (is (= 72 (average-of-cubes [2 4 6])))
  (is (= 0 (average-of-cubes nil)))
  (is (= 0 (average-of-cubes []))))

(run-test test-variation)
