(defn split_Arr
  "	Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [v (vec (or l []))
        idx (max 0 (min n (dec (count v))))]
    (concat (subvec v idx) (subvec v 0 idx))))