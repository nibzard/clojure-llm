(defn count_occurance
  "	Write a function to count the number of occurence of the string 'std' in a given string."
  [s]
  (let [pattern "std"]
    (if (and (string? s) (seq pattern))
      (count (re-seq (re-pattern pattern) s))
      0)))