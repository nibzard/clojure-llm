(defn count_occurance
  "	Write a function to count the number of occurence of the string 'std' in a given string."
  [s]
  (if (seq s)
    (count (re-seq #"std" s))
    0))