(defn number_ctr
  "Write a cljthon function to count number of digits in a given string."
  [str]
  (if (string? str)
    (count (filter #(Character/isDigit %) str))
    0))