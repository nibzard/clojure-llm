(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [n (count input)
        k (cond
            (nil? k) 0
            (neg? k) 0
            :else (min k n))]
    (vec (concat (reverse (subvec (vec input) 0 k))
                 (subvec (vec input) k n)))))