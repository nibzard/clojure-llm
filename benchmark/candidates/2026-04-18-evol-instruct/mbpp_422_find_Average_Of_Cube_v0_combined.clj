(defn average-of-cubes
  "Return the average of the cubes of the first n natural numbers.
   Works with any integer n >= 0.

   Examples:
   (average-of-cubes 1) => 1/1
   (average-of-cubes 3) => 12
   (average-of-cubes 0) => 0"
  [n]
  (if (zero? n)
    0
    (/ (reduce + (map #(Math/pow % 3) (range 1 (inc n))))
       n)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 12 (average-of-cubes 3))))

(run-test test-variation)
