(defn minSubArraySum
  "Given a vector of integers nums, find the minimum sum of any non-empty sub-vector
  of nums.
  Example
  >>> (minSubArraySum [2 3 4 1 2 4])
  1
  >>> (minSubArraySum [-1 -2 -3])
  -6"
  [nums]
  (if (empty? nums)
    0
    (let [inverted (map - nums)]
      (reduce (fn [[global-min current-min] x]
                (let [new-current (min x (+ current-min x))
                      new-global (min global-min new-current)]
                  [new-global new-current]))
              [(first inverted) (first inverted)]
              (rest inverted)
              first))))