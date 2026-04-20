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
  [numbers]
  (let [sorted (sort numbers)
        n (count sorted)]
    (if (zero? n)
      0.0
      (let [mid (quot n 2)
            median (if (odd? n)
                     (nth sorted mid)
                     (/ (+ (nth sorted (dec mid))
                           (nth sorted mid))
                        2.0))
            deviations (sort (map #(Math/abs (double (- % median))) numbers))
            m (count deviations)
            mid2 (quot m 2)]
        (if (odd? m)
          (double (nth deviations mid2))
          (/ (+ (double (nth deviations (dec mid2)))
                (double (nth deviations mid2)))
             2.0))))))