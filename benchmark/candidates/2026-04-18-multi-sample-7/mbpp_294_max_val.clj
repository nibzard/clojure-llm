(defn max_val
  "	Write a function to find the maximum value in a given heterogeneous list."
  [listval]
  (let [nums (filter number? listval)]
    (when (seq nums)
      (reduce max nums))))