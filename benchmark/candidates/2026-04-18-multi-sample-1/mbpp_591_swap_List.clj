(defn swap_List
  "	Write a cljthon function to interchange the first and last elements in a list."
  [newList]
  (let [v (vec newList)
        n (count v)]
    (cond
      (<= n 1) newList
      :else (seq (assoc v 0 (v (dec n)) (dec n) (v 0))))))