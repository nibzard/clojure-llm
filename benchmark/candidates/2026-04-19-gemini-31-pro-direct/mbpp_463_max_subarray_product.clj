(defn max_subarray_product
  "Write a function to find the maximum product subvector of the given vector."
  [arr]
  (when (seq arr)
    (first
     (reduce (fn [[g-max c-max c-min] x]
               (let [p1 (* c-max x)
                     p2 (* c-min x)
                     n-max (max x p1 p2)
                     n-min (min x p1 p2)]
                 [(max g-max n-max) n-max n-min]))
             [(first arr) (first arr) (first arr)]
             (rest arr)))))