(defn rescale_to_unit
  " Given list of numbers (of at least two elements), apply a linear transform to that list,
  such that the smallest number will become 0 and the largest will become 1
  >>> (rescale_to_unit [1.0 2.0 3.0 4.0 5.0])
  [0.0 0.25 0.5 0.75 1.0]"
  [numbers]
  (let [nums  (seq numbers)
        min-v (reduce min nums)
        max-v (reduce max nums)
        span  (- max-v min-v)]
    (mapv (fn [x]
            (if (zero? span)
              0.0
              (/ (- x min-v) span)))
          numbers)))