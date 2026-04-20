(defn big_diff
  "	Write a cljthon function to find the difference between largest and smallest value in a given list."
  [nums]
  (when (seq nums)
    (- (apply max nums) (apply min nums))))