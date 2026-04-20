(defn convert_list_dictionary
  "Write a function to convert more than one list to nested map."
  [l1 l2 l3]
  (mapv (fn [k1 k2 v] {k1 {k2 v}}) l1 l2 l3))