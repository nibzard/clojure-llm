(defn count
  "Write a cljthon function to count true booleans in the given list."
  [lst]
  (if (nil? lst)
    0
    (count true? lst)))