(defn split
  "	Write a cljthon function to split a string into characters."
  [word]
  (if (nil? word)
    []
    (mapv str word)))