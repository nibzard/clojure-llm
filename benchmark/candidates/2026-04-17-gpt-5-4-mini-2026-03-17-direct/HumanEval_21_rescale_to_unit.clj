(defn rescale_to_unit
  " Given list of numbers (of at least two elements), apply a linear transform to that list,
  such that the smallest number will become 0 and the largest will become 1
  >>> (rescale_to_unit [1.0 2.0 3.0 4.0 5.0])
  [0.0 0.25 0.5 0.75 1.0]"
  [numbers]
  (let [mn (apply min numbers)
        mx (apply max numbers)
        range (- mx mn)]
    (mapv (fn [n]
            (if (zero? range)
              0.0
              (/ (- n mn) range)))
          numbers)))