(defn pack_consecutive_duplicates
  "	Write a function to pack consecutive duplicates of a given list elements into sublists."
  [list1]
  (when (seq list1)
    (reduce
      (fn [acc x]
        (if (= x (last (last acc)))
          (conj (vec (butlast acc)) (conj (last acc) x))
          (conj acc [x])))
      [[(first list1)]]
      (rest list1))))