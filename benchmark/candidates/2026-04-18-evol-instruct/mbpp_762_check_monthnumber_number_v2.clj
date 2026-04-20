(defn sum-months-with-thirty-days
  "Return the sum of all month numbers in `months` whose corresponding month has 30 days.
  Accepts a collection of month numbers (1-12), ignores invalid values, and returns 0 for nil or empty input.

  Examples:
  (sum-months-with-thirty-days [1 4 6 9 11]) => 30
  (sum-months-with-thirty-days [2 3 4 13 nil 6]) => 10
  (sum-months-with-thirty-days nil) => 0"
  [months]
  (let [thirty-day-months #{4 6 9 11}]
    (transduce (filter thirty-day-months)
               +
               0
               (or months []))))