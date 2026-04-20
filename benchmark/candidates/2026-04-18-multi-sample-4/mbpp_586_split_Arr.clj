(defn split_Arr
  "	Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [coll (or l '())
        cnt  (count coll)
        k    (if (pos? cnt) (mod (max 0 n) cnt) 0)]
    (concat (drop k coll) (take k coll))))