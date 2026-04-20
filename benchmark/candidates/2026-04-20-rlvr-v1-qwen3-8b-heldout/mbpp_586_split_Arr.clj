(defn split_Arr
  "	Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [v (vec (or l []))
        cnt (count v)
        idx (mod n cnt)]
    (if (zero? cnt)
      []
      (let [a (subs v 0 idx)
            b (subs v idx)]
        (concat b a)))))