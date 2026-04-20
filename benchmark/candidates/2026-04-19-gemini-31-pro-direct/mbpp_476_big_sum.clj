(defn big_sum
  "Write a cljthon function to find the sum of the largest and smallest value in a given vector."
  [nums]
  (if (empty? nums)
    0
    (+ (apply min nums) (apply max nums))))