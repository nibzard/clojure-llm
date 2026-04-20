(defn swap_List
  "	Write a cljthon function to interchange the first and last elements in a list."
  [newList]
  (let [s (seq newList)]
    (cond
      (nil? s) newList
      (nil? (next s)) newList
      :else (let [v (vec s)
                  n (count v)]
              (apply list
                     (assoc v
                            0 (v (dec n))
                            (dec n) (v 0)))))))