(defn normalize-range
  "Given a vector of numbers (at least two elements), return a vector of the same
order where the smallest value becomes 0.0 and the largest becomes 1.0.

If all numbers are equal, return a vector of 0.0 values.

Works with integers, ratios, and doubles.

Examples:
(normalize-range [2 4 6 8])
[0.0 0.3333333333333333 0.6666666666666666 1.0]

(normalize-range [5 5 5])
[0.0 0.0 0.0]"
  [nums]
  (let [mn (apply min nums)
        mx (apply max nums)
        span (- mx mn)]
    (vec (if (zero? span)
           (repeat (count nums) 0.0)
           (map #(double (/ (- % mn) span)) nums)))))