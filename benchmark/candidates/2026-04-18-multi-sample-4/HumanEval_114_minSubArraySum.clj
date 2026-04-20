(defn minSubArraySum
  "Given a vector of integers nums, find the minimum sum of any non-empty sub-vector
  of nums.
  Example
  >>> (minSubArraySum [2 3 4 1 2 4])
  1
  >>> (minSubArraySum [-1 -2 -3])
  -6"
  [nums]
  (when-let [s (seq nums)]
    (let [x0 (first s)]
      (second
       (reduce (fn [[curr-min best-min] x]
                 (let [curr-min (min x (+ curr-min x))
                       best-min (min best-min curr-min)]
                   [curr-min best-min]))
               [x0 x0]
               (rest s))))))