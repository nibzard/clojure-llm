(defn split_Arr
  "	Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [v (vec l)
        idx (max 0 (min (or n 0) (dec (count v))))
        a (subvec v 0 (inc idx))
        b (subvec v (inc idx))]
    (concat b a)))