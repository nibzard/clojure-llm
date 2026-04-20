(defn big_diff
  "Write a cljthon function to find the difference between largest and smallest value in a given list."
  [nums]
  (if (empty? nums)
    0
    (- (apply max nums) (apply min nums))))