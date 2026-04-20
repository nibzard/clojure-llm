(defn pack_consecutive_duplicates
  "Write a function to pack consecutive duplicates of a given list elements into sublists."
  [list1]
  (when (seq list1)
    (reduce (fn [acc x]
              (if (= x (first (peek acc)))
                (conj (pop acc) (conj (peek acc) x))
                (conj acc [x])))
            [[(first list1)]]
            (rest list1))))