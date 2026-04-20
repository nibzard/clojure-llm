(defn split_two_parts
  "	Write a function that takes in a list and an integer L and splits the given list into two parts where the length of the first part of the list is L, and returns the resulting lists in a vector."
  [list1 L]
  (let [n (max 0 (min L (count list1)))]
    [(take n list1) (drop n list1)]))