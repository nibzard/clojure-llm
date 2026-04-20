(defn min_val
  "	Write a function to find the minimum value in a given heterogeneous list."
  [listval]
  (when (seq listval)
    (reduce (fn [mn x]
              (cond
                (nil? mn) x
                (nil? x) mn
                (number? mn) (if (number? x) (min mn x) mn)
                (number? x) x
                :else mn))
            nil
            listval)))