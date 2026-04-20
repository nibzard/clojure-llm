(defn pack_consecutive_duplicates
  "Write a function to pack consecutive duplicates of a given list elements into sublists."
  [list1]
  (when (seq list1)
    (reduce (fn [acc x]
              (let [grp (peek acc)]
                (if (= x (first grp))
                  (conj (pop acc) (conj grp x))
                  (conj acc [x]))))
            [[(first list1)]]
            (rest list1))))