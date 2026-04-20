(defn max_subarray_product
  "	Write a function to find the maximum product subvector of the given vector."
  [arr]
  (when (seq arr)
    (let [[max-prod _ _]
          (reduce
            (fn [[max-so-far max-ending min-ending] x]
              (let [candidates [x (* max-ending x) (* min-ending x)]
                    new-max (apply max candidates)
                    new-min (apply min candidates)]
                [(max max-so-far new-max) new-max new-min]))
            [(first arr) (first arr) (first arr)]
            (rest arr))]
      max-prod)))