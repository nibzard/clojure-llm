(defn empty_list
  "	Write a function to create a list of N empty dictionaries."
  [length]
  (if (and (integer? length) (pos? length))
    (repeat length {})
    '()))