(defn reverse_Array_Upto_K
  "	Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (let [v (vec (or input []))
        n (count v)
        k (cond
            (nil? k) -1
            (neg? k) -1
            :else (min k (dec n)))]
    (if (or (zero? n) (neg? k))
      v
      (vec (concat (reverse (subvec v 0 (inc k)))
                   (subvec v (inc k)))))))