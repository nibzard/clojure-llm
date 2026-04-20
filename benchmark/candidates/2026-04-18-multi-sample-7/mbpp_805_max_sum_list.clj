(defn max_sum_list
  "	Write a function that returns the list in a list of lists whose sum of elements is the highest."
  [lists]
  (when (seq lists)
    (reduce (fn [best xs]
              (if (> (reduce + 0 xs) (reduce + 0 best))
                xs
                best))
            lists)))