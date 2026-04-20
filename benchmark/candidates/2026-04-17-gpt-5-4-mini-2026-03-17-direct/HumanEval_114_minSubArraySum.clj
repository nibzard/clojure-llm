(defn minSubArraySum
  "Given a vector of integers nums, find the minimum sum of any non-empty sub-vector
  of nums."
  [nums]
  (when (seq nums)
    (let [[best _]
          (reduce (fn [[best current] x]
                    (let [current (min x (+ current x))
                          best (min best current)]
                      [best current]))
                  [(first nums) (first nums)]
                  (rest nums))]
      best)))