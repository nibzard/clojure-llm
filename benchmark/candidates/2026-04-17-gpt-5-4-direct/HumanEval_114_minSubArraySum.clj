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
      (first
       (reduce (fn [[best-ending best-overall] n]
                 (let [best-ending' (min n (+ best-ending n))]
                   [best-ending' (min best-overall best-ending')]))
               [x x]
               (rest s))))))