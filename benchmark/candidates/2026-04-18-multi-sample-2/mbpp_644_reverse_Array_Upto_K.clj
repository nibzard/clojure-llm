(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [v (vec (or input []))
        n (count v)]
    (cond
      (zero? n) v
      (nil? k) v
      :else
      (let [idx (max 0 (min (dec n) k))]
        (vec (concat (reverse (subvec v 0 (inc idx)))
                     (subvec v (inc idx))))))))