(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [v (vec (or input []))
        n (count v)
        k (max 0 (min k (dec n)))]
    (vec (concat (subvec v 0 (inc k))
                 (reverse (subvec v 0 (inc k)))
                 (subvec v (inc k) n)))))