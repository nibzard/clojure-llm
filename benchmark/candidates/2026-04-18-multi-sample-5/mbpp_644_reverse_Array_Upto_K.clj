(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [v (vec (or input []))
        n (count v)
        k (cond
            (nil? k) 0
            (neg? k) 0
            :else (min k n))]
    (vec (concat (reverse (subvec v 0 k))
                 (subvec v k n)))))