(defn sum_negativenum
  "	Write a function to calculate the sum of the negative numbers of a given list of numbers."
  [nums]
  (reduce + 0 (filter neg? nums)))