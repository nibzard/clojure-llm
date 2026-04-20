(defn split_two_parts
  "	Write a function that takes in a list and an integer L and splits the given list into two parts where the length of the first part of the list is L, and returns the resulting lists in a vector."
  [list1 L]
  (let [n (max 0 (or L 0))
        xs (or list1 '())]
    [(take n xs) (drop n xs)]))