(defn find_Element
  "Finds the element at a given index after a number of range rotations.
   Each rotation shifts the elements in the range [L, R] to the right by 1."
  [arr ranges rotations index]
  (let [original-index (reduce (fn [idx [l r]]
                                 (if (<= l idx r)
                                   (if (= idx l)
                                     r
                                     (dec idx))
                                   idx))
                               index
                               (reverse (take rotations ranges)))]
    (nth arr original-index)))