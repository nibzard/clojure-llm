(defn max_val
  "	Write a function to find the maximum value in a given heterogeneous list."
  [listval]
  (let [vals (seq (filter number? (flatten listval)))]
    (when vals
      (reduce max vals))))