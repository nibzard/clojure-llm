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
      (reduce (fn [[current-min overall-min] x]
                (let [current-min (min x (+ current-min x))
                      overall-min (min overall-min current-min)]
                  [current-min overall-min]))
              [x0 x0]
              (rest s))
      |> second)))