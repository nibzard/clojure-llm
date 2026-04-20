(defn count_samepair
  "Write a function to count number items that are identical in the same position of three given lists."
  [list1 list2 list3]
  (count (filter true? (map #(and (= %1 %2) (= %2 %3)) list1 list2 list3))))