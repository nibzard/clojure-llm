(defn max_length_list
  "	Write a function to find the list with maximum length."
  [input_list]
  (when (seq input_list)
    (reduce (fn [max-list current]
              (if (> (count current) (count max-list))
                current
                max-list))
            input_list)))