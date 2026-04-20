(defn round_and_sum
  "Write a function to round every number of a given list of numbers and print the total sum multiplied by the length of the list."
  [list1]
  (let [rounded (map Math/round list1)
        total (apply + 0 rounded)
        length (count rounded)]
    (println (* total length))))