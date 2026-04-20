(defn max_subarray_product
  "	Write a function to find the maximum product subvector of the given vector."
  [arr]
  (when (seq arr)
    (let [[result _ _]
          (reduce (fn [[best max-ending min-ending] x]
                    (let [candidates (if (neg? x)
                                       [x min-ending max-ending]
                                       [x max-ending min-ending])
                          new-max (max x (* x (second candidates)))
                          new-min (min x (* x (nth candidates 2)))
                          new-best (max best new-max)]
                      [new-best new-max new-min]))
                  [(first arr) (first arr) (first arr)]
                  (rest arr))]
      result)))