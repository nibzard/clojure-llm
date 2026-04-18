(defn max_length_list
  "	Write a function to find the list with maximum length."
  [input_list]
  (when (seq input_list)
    (reduce (fn [best x]
              (if (> (count x) (count best))
                x
                best))
            input_list)))