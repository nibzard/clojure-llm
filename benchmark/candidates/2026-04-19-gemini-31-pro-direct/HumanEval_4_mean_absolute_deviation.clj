(defn mean_absolute_deviation
  "For a given list of input numbers, calculate Mean Absolute Deviation
  around the mean of this dataset.
  Mean Absolute Deviation is the average absolute difference between each
  element and a centerpoint (mean in this case):
  MAD = average | x - x_mean |"
  [numbers]
  (if (empty? numbers)
    0.0
    (let [n (count numbers)
          mean (/ (double (apply + numbers)) n)
          abs-diffs (map #(Math/abs (double (- % mean))) numbers)]
      (/ (apply + abs-diffs) n))))