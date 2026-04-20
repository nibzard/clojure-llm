(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [v (vec (or input []))
        n (count v)
        idx (min (max 0 (or k 0)) n)]
    (vec (concat (reverse (subvec v 0 idx))
                 (subvec v idx n)))))