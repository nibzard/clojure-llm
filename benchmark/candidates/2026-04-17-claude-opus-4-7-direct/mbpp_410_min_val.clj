(defn min_val
  "Write a function to find the minimum value in a given heterogeneous list."
  [listval]
  (if (empty? listval)
    nil
    (apply min listval)))