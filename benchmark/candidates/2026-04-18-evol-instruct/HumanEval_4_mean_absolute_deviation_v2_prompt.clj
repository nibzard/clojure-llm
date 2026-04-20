(defn median_absolute_deviation
  "For a collection of numbers, calculate the median absolute deviation (MAD)
  around the median of the dataset.

  MAD = median(|x - median(x)|)

  Requirements:
  - Accept any sequential collection of numbers
  - Ignore nil values
  - Return 0.0 for an empty collection or a collection with only nils

  Examples:
  (median_absolute_deviation [1 2 3 4 5])
  => 1.0

  (median_absolute_deviation [1 nil 2 100])
  => 1.0"
  [numbers])