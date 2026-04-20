(defn smallest_num
  "Write a cljthon function to find smallest number in a list."
  [xs]
  (if (empty? xs)
    nil
    (apply min xs)))