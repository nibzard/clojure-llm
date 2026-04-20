(defn convert_list_dictionary
  "Write a function to convert more than one list to nested map."
  [l1 l2 l3]
  (into {}
        (for [k l1
              :let [nested (into {}
                                (for [k2 l2
                                      :let [inner (into {}
                                                       (for [k3 l3]
                                                         [k3 nil]))]
                                      :when inner]
                                  [k2 inner]))]
              :when nested]
          [k nested])))