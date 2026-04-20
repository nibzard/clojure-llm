(defn sort_sublists
  "	Write a function to sort each sublist of strings in a given list of lists."
  [input_list]
  (when (some? input_list)
    (mapv (fn [sublist]
            (if (some? sublist)
              (sort sublist)
              sublist))
          input_list)))