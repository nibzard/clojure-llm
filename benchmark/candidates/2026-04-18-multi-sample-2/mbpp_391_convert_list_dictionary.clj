(defn convert_list_dictionary
  "	Write a function to convert more than one list to nested map."
  [l1 l2 l3]
  (into {}
        (map (fn [k v]
               [k (into {}
                        (map (fn [k2 v2] [k2 v2]) l2 v))])
             l1
             l3)))