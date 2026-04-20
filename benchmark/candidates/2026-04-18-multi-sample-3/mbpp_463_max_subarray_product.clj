(defn max_subarray_product
  "	Write a function to find the maximum product subvector of the given vector."
  [arr]
  (when (seq arr)
    (let [[best _ _]
          (reduce (fn [[best-so-far max-ending min-ending] x]
                    (let [candidates [x (* x max-ending) (* x min-ending)]
                          new-max (apply max candidates)
                          new-min (apply min candidates)
                          new-best (max best-so-far new-max)]
                      [new-best new-max new-min]))
                  [(first arr) (first arr) (first arr)]
                  (rest arr))]
      best)))