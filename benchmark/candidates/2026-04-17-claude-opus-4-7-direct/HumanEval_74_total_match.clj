(defn total_match
  "Write a function that accepts two lists of strings and returns the list that has 
  total number of chars in the all strings of the list less than the other list.
  if the two lists have the same number of chars, return the first list."
  [lst1 lst2]
  (let [count-chars (fn [lst] (reduce (fn [acc s] (+ acc (count s))) 0 lst))
        c1 (count-chars lst1)
        c2 (count-chars lst2)]
    (if (<= c1 c2) lst1 lst2)))