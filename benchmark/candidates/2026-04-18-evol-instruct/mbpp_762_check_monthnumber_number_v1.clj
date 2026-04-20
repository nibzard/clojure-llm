(defn months-with-30-days?
  "Return true if every month number in the collection is one of the 30-day months (4, 6, 9, 11).
Examples:
  (months-with-30-days? [4 6 9]) => true
  (months-with-30-days? [4 5 6]) => false
  (months-with-30-days? nil) => true"
  [monthnums]
  (every? #{4 6 9 11} monthnums))