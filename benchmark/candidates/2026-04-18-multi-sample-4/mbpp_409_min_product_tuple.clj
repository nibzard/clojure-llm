(defn min_product_tuple
  "	Write a function to find the minimum product from the pairs of vectors within a given list."
  [list1]
  (when (seq list1)
    (reduce min
            (map (fn [pair]
                   (if (and (vector? pair) (= 2 (count pair)))
                     (let [[a b] pair]
                       (* a b))
                     (throw (ex-info "Each element must be a vector of two numbers." {:element pair}))))
                 list1))))