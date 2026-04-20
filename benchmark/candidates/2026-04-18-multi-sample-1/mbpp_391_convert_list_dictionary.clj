(defn convert_list_dictionary
  "Write a function to convert more than one list to nested map."
  [l1 l2 l3]
  (into {}
        (map (fn [a b c]
               [a {b c}])
             l1 l2 l3)))