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
    (let [x (first s)]
      (reduce (fn [[current-min global-min] n]
                (let [current-min (min n (+ current-min n))]
                  [current-min (min global-min current-min)]))
              [x x]
              (rest s))
      |> second)))