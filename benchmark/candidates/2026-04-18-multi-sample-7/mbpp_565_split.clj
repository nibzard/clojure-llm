(defn split
  "	Write a cljthon function to split a string into characters."
  [word]
  (when (some? word)
    (mapv str word)))