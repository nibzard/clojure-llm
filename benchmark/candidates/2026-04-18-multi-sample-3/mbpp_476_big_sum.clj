(defn big_sum
  "	Write a cljthon function to find the sum of the largest and smallest value in a given vector."
  [nums]
  (when-let [xs (seq nums)]
    (+ (apply min xs) (apply max xs))))