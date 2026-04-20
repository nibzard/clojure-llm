(defn count_samepair
  "	Write a function to count number items that are identical in the same position of three given lists."
  [list1 list2 list3]
  (->> (map vector list1 list2 list3)
       (filter (fn [[a b c]] (= a b c)))
       count))