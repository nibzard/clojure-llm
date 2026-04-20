(defn make_a_pile [n]
  (vec (take n (iterate #(+ 2 %) n))))