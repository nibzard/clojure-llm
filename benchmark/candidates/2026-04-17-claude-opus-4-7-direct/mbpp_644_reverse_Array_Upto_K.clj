(defn reverse_Array_Upto_K
  "Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (if (or (nil? input) (empty? input) (<= k 0))
    input
    (let [k (min k (count input))]
      (into (subvec input k) (rseq (subvec input 0 k))))))