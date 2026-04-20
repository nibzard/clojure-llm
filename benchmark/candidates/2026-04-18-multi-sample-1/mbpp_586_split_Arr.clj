(defn split_Arr
  "Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [v (vec (or l []))
        cnt (count v)
        k (cond
            (zero? cnt) 0
            (nil? n) 0
            :else (mod n cnt))]
    (concat (subvec v k cnt) (subvec v 0 k))))