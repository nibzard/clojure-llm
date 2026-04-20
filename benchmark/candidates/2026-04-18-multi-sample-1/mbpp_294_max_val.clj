(defn max_val
  "	Write a function to find the maximum value in a given heterogeneous list."
  [listval]
  (when (seq listval)
    (reduce (fn [mx x]
              (cond
                (nil? mx) x
                (nil? x) mx
                (number? mx)
                (if (number? x)
                  (max mx x)
                  mx)
                (number? x) x
                :else mx))
            nil
            listval)))