(defn minSubArraySum
  "Given a vector of integers nums, find the minimum sum of any non-empty sub-vector
  of nums."
  [nums]
  (when (seq nums)
    (second
     (reduce (fn [[curr-min global-min] x]
               (let [curr-min (min x (+ curr-min x))]
                 [curr-min (min global-min curr-min)]))
             [(first nums) (first nums)]
             (rest nums)))))