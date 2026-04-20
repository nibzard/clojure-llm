(defn combinations_list
  "	Write a function to find all possible combinations of the elements of a given list."
  [list1]
  (let [items (vec (or list1 []))]
    (reduce
      (fn [acc x]
        (into acc (map #(conj % x) acc)))
      [[]]
      items)))