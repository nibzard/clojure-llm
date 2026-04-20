(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [v (vec (or input []))
        n (count v)
        limit (min n (max 0 (or k 0)))]
    (vec (concat (reverse (subvec v 0 limit))
                 (subvec v limit)))))