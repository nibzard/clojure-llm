(defn extract_index_list
  "We say that an element is common for lists l1, l2, l3 if it appears in all three lists under the same index. Write a function to find common elements from three lists. The function should return a list."
  [l1 l2 l3]
  (->> (map vector l1 l2 l3)
       (filter (fn [[a b c]] (= a b c)))
       (map first)
       (apply list)))