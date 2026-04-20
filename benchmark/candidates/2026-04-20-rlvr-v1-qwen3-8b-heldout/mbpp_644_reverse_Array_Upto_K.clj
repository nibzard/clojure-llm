(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [n (count input)]
    (if (or (<= k 0) (nil? input) (>= k n))
      input
      (vec (concat (seq (reverse (subvec input 0 k)))
                   (subvec input k))))))