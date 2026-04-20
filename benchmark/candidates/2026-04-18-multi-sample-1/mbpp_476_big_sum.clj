(defn big_sum
  "	Write a cljthon function to find the sum of the largest and smallest value in a given vector."
  [nums]
  (when (seq nums)
    (+ (apply min nums) (apply max nums))))