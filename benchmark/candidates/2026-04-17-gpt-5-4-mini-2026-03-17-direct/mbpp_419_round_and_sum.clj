(defn round_and_sum
  "Write a function to round every number of a given list of numbers and print the total sum multiplied by the length of the list."
  [list1]
  (let [rounded (map #(Math/round (double %)) list1)
        total   (reduce + 0 rounded)
        n       (count list1)]
    (* total n)))