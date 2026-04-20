(defn round_and_sum
  "	Write a function to round every number of a given list of numbers and print the total sum multiplied by the length of the list."
  [list1]
  (let [nums    (or list1 [])
        rounded (map #(Math/round (double %)) nums)
        total   (reduce + 0 rounded)]
    (* total (count nums))))