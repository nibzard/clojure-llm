(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [v (vec (or input []))
        n (count v)
        idx (cond
              (nil? k) -1
              (neg? k) -1
              :else (min (dec n) k))]
    (if (or (zero? n) (neg? idx))
      v
      (vec (concat (reverse (subvec v 0 (inc idx)))
                   (subvec v (inc idx)))))))