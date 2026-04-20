(defn split_Arr
  "	Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [coll (or l '())
        cnt  (count coll)
        idx  (cond
               (<= cnt 0) 0
               (neg? n)   (mod n cnt)
               :else      (min n cnt))]
    (concat (drop idx coll) (take idx coll))))