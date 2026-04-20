(defn count
  "	Write a cljthon function to count true booleans in the given list."
  [lst]
  (reduce (fn [n x] (if (true? x) (inc n) n)) 0 lst))