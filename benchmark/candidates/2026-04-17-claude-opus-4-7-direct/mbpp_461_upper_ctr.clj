(defn upper_ctr
  "Write a cljthon function to count the upper case characters in a given string."
  [str]
  (if (string? str)
    (count (filter #(Character/isUpperCase %) str))
    0))