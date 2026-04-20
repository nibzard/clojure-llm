(defn convert_list_dictionary
  "	Write a function to convert more than one list to nested map."
  [l1 l2 l3]
  (into {}
        (map (fn [k1 k2 k3]
               [k1 {k2 k3}])
             l1 l2 l3)))