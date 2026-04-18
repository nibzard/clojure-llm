(defn count_occurance
  "	Write a function to count the number of occurence of the string 'std' in a given string."
  [s]
  (if (nil? s)
    0
    (count (re-seq #"std" (str s)))))