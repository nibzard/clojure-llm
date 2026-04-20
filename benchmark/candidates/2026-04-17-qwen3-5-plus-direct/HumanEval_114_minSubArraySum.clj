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
    nil
    (let [first-elem (first nums)
          result (reduce (fn [[min-ending-here min-so-far] x]
                           (let [new-min-ending (min x (+ min-ending-here x))]
                             [new-min-ending (min min-so-far new-min-ending)]))
                         [first-elem first-elem]
                         (rest nums))]
      (second result))))