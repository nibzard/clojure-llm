(defn frequency
  "Write a function to count the number of occurrences of a number in a given list."
  [a x]
  (count (filter #(= x %) a)))