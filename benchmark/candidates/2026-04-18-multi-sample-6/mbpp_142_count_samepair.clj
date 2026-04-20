(defn count_samepair
  "	Write a function to count number items that are identical in the same position of three given lists."
  [list1 list2 list3]
  (count
   (filter true?
           (map (fn [a b c] (= a b c)) list1 list2 list3))))