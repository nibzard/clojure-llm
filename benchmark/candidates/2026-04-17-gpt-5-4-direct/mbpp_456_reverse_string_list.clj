(defn reverse_string_list
  "	Write a function to reverse each string in a given list of string values."
  [stringlist]
  (mapv #(apply str (reverse (or % ""))) (or stringlist [])))