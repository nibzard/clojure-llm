(defn median-range
  "Return the median value of a collection of numbers.

  The function must work for both finite and lazy/infinite sorted sequences.
  If the collection has an even number of elements, return the average of the
  two middle values.

  Examples:
  (median-range [1 3 2])     ;=> 2
  (median-range [1 2 3 4])   ;=> 2.5
  (median-range (take 5 (range))) ;=> 2"
  [xs])