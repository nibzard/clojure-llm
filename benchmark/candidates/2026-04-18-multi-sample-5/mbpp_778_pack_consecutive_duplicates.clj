(defn pack_consecutive_duplicates
  "	Write a function to pack consecutive duplicates of a given list elements into sublists."
  [list1]
  (when (seq list1)
    (reduce
      (fn [acc x]
        (let [group (peek acc)]
          (if (= x (first group))
            (conj (pop acc) (conj group x))
            (conj acc [x]))))
      [[(first list1)]]
      (rest list1))))