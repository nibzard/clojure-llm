(defn max_subarray_product
  "	Write a function to find the maximum product subvector of the given vector."
  [arr]
  (when (seq arr)
    (let [x0 (first arr)]
      (first
       (reduce
        (fn [[best-so-far max-ending min-ending] x]
          (let [c1 x
                c2 (* x max-ending)
                c3 (* x min-ending)
                new-max (max c1 c2 c3)
                new-min (min c1 c2 c3)
                new-best (max best-so-far new-max)]
            [new-best new-max new-min]))
        [x0 x0 x0]
        (rest arr)))))