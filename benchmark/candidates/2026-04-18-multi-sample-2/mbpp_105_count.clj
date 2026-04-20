(defn count
  "	Write a cljthon function to count true booleans in the given list."
  [lst]
  (reduce (fn [acc x] (if (true? x) (inc acc) acc)) 0 lst))