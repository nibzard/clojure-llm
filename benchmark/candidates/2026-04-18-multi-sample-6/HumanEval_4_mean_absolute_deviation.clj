(defn mean_absolute_deviation
  " For a given list of input numbers, calculate Mean Absolute Deviation
  around the mean of this dataset.
  Mean Absolute Deviation is the average absolute difference between each
  element and a centerpoint (mean in this case):
  MAD = average | x - x_mean |
  >>> (mean_absolute_deviation [1.0 2.0 3.0 4.0])
  1.0"
  [numbers]
  (when (seq numbers)
    (let [cnt (count numbers)
          mean (/ (reduce + numbers) cnt)]
      (/ (reduce + (map #(Math/abs (double (- % mean))) numbers))
         cnt))))