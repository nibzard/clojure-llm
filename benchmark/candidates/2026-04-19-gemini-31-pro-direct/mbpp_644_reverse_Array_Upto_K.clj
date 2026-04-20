(defn reverse_Array_Upto_K
  "Write a cljthon function to reverse a vector upto a given position."
  [input k]
  (if (empty? input)
    []
    (let [k (max 0 k)
          [head tail] (split-at k input)]
      (vec (concat (reverse head) tail)))))