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
  [numbers]
  (let [xs (sort (remove nil? numbers))
        n (count xs)]
    (if (zero? n)
      0.0
      (let [median (if (odd? n)
                     (double (nth xs (quot n 2)))
                     (/ (+ (double (nth xs (dec (quot n 2))))
                           (double (nth xs (quot n 2))))
                        2.0))
            deviations (sort (map #(Math/abs (double (- % median))) xs))
            m (count deviations)]
        (if (odd? m)
          (double (nth deviations (quot m 2)))
          (/ (+ (double (nth deviations (dec (quot m 2))))
                (double (nth deviations (quot m 2))))
             2.0))))))