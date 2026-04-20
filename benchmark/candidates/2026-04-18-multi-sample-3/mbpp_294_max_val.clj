(defn max_val
  "	Write a function to find the maximum value in a given heterogeneous list."
  [listval]
  (when (seq listval)
    (reduce (fn [mx x]
              (if (and (number? x)
                       (or (nil? mx) (> x mx)))
                x
                mx))
            nil
            listval)))