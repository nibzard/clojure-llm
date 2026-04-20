(defn count_charac
  "Write a function to count the total number of characters in a string."
  [str1]
  (if (string? str1)
    (count str1)
    0))