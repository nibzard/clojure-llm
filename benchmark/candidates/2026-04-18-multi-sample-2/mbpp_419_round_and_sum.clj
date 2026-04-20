(defn round_and_sum
  "	Write a function to round every number of a given list of numbers and print the total sum multiplied by the length of the list."
  [list1]
  (let [nums (or list1 [])
        rounded-sum (reduce + 0 (map #(Math/round (double %)) nums))]
    (* rounded-sum (count nums))))