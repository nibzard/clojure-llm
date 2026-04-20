(defn count-occurance
  "Write a function to count the number of occurence of the string 'std' in a given string."
  [s]
  (if (string? s)
    (count (re-seq #"std" s))
    0))