(defn digitSum
  "Task
  Write a function that takes a string as input and returns the sum of the upper characters only'
  ASCII codes."
  [s]
  (->> s
       (filter #(Character/isUpperCase %))
       (map int)
       (reduce + 0)))