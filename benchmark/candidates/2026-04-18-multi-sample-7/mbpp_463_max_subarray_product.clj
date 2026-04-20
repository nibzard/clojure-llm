(defn max_subarray_product
  "	Write a function to find the maximum product subvector of the given vector."
  [arr]
  (when (seq arr)
    (let [step (fn [[max-prod min-prod best] x]
                 (let [candidates [x (* x max-prod) (* x min-prod)]
                       new-max (apply max candidates)
                       new-min (apply min candidates)]
                   [new-max new-min (max best new-max)]))
          x0 (first arr)]
      (nth
       (reduce step [x0 x0 x0] (rest arr))
       2))))