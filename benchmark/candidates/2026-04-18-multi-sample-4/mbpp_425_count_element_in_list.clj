(defn count_element_in_list
  "	Write a function to count the number of sublists containing a particular element."
  [list1 x]
  (count
   (filter #(and (sequential? %) (some (fn [e] (= e x)) %))
           list1)))