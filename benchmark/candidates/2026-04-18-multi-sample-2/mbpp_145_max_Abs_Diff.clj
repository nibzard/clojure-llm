(defn max_Abs_Diff
  "	Write a cljthon function to find the maximum difference between any two elements in a given vector."
  [arr]
  (when (seq arr)
    (- (apply max arr) (apply min arr))))