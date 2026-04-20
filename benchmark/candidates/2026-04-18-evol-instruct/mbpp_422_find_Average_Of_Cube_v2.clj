(defn average-of-cubes
  "Return the average of the cubes of the first n natural numbers.

  Examples:
  (average-of-cubes 1) => 1/1
  (average-of-cubes 3) => 12/1

  If n is nil or less than 1, return 0."
  [n]
  (if (and n (pos? n))
    (/ (reduce + (map #(Math/pow % 3) (range 1 (inc n)))) n)
    0))