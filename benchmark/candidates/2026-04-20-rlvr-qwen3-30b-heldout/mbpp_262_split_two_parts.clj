(defn split_two_parts
  "	Write a function that takes in a list and an integer L and splits the given list into two parts where the length of the first part of the list is L, and returns the resulting lists in a vector."
  [list1 L]
  (let [n (max 0 (min (count list1) (or L 0)))]
    [(vec (take n list1)) (vec (drop n list1))]))