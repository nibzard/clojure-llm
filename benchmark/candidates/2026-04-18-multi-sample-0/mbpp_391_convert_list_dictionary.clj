(defn convert_list_dictionary
  "	Write a function to convert more than one list to nested map."
  [l1 l2 l3]
  (into {}
        (map (fn [a b]
               [a (into {}
                        (map (fn [c d] [c d]) l2 b))])
             l1
             l3)))