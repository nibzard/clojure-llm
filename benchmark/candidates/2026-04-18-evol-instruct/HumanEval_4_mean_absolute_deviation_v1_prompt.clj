(defn median_absolute_deviation
  "For a collection of numbers, calculate the Median Absolute Deviation (MAD)
  around the median of the dataset.

  MAD = median(|x - median(numbers)|)

  Return 0.0 for an empty collection.

  Examples:
  (median_absolute_deviation [1.0 2.0 3.0 4.0 5.0])
  => 1.0

  (median_absolute_deviation [10 10 11 12 100])
  => 1.0"
  [numbers])