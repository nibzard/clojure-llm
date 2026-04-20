(defn max_length_list
  "	Write a function to find the list with maximum length."
  [input_list]
  (when (seq input_list)
    (reduce (fn [max-list list]
              (if (> (count list) (count max-list))
                list
                max-list))
            input_list)))