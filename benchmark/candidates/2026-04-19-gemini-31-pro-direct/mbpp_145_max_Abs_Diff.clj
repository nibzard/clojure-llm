(defn max_Abs_Diff
  "Write a cljthon function to find the maximum difference between any two elements in a given vector."
  [arr]
  (if (empty? arr)
    0
    (- (apply max arr) (apply min arr))))