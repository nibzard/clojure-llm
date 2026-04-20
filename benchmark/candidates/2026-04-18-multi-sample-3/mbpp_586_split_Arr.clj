(defn split_Arr
  "Write a cljthon function to split a list at the nth eelment and add the first part to the end."
  [l n]
  (let [coll (or l '())
        cnt  (count coll)
        n    (cond
               (zero? cnt) 0
               (neg? n) (mod n cnt)
               :else (min n cnt))]
    (concat (drop n coll) (take n coll))))