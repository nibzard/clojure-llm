(defn smallest_num
  "	Write a cljthon function to find smallest number in a list."
  [xs]
  (when (seq xs)
    (apply min xs)))