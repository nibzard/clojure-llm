(defn rearrange_bigger [n]
  (let [digits (vec (str n))
        len (count digits)
        i (last (filter #(< (compare (digits (dec %)) (digits %)) 0) (range 1 len)))]
    (if-not i
      -1
      (let [pivot-idx (dec i)
            pivot (digits pivot-idx)
            j (last (filter #(< (compare pivot (digits %)) 0) (range i len)))
            swapped (assoc digits pivot-idx (digits j) j pivot)
            res (concat (subvec swapped 0 i) (reverse (subvec swapped i)))]
        (Long/parseLong (apply str res))))))